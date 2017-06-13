package com.example.root.provaremorzinho.SQLite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.root.provaremorzinho.DAO.CondoDAO;
import com.example.root.provaremorzinho.POJO.Condominio;
import com.example.root.provaremorzinho.R;

public class EditaActivity extends AppCompatActivity {
    private Button btEditar;
    private EditText etNome, etArea;
    private CheckBox cbElevador;
    private Spinner spinner;
    private Condominio condominio, novoCondominio;

    private CondoDAO dao;

    private String nome, area;
    private String elevador = "";
    private String aps = "";
    private String id;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita);
        inicializaComponentes();
        etNome.setText(condominio.getNome().toString());
        etArea.setText(condominio.getArea().toString());

        if(condominio.getElevador().equals("sim")) {cbElevador.toggle();}
        String tag = "erro";
        Log.i(tag, "onCreate: " + condominio.getPosicao());
        spinner.setSelection(condominio.getPosicao());


        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = etNome.getText().toString();
                area = etArea.getText().toString();
                if (cbElevador.isChecked()) { elevador = "sim"; } else { elevador = "nao"; };

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        aps = spinner.getItemAtPosition(position).toString();
                        posicao = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                novoCondominio= new Condominio(nome, elevador, aps, area, posicao);
                novoCondominio.setId(condominio.getId());
                Intent intent = new Intent(EditaActivity.this, Lista.class);
                boolean editou = dao.editar(novoCondominio);
                if (editou) {
                    Toast.makeText(EditaActivity.this, "Salvo", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(EditaActivity.this, "Não salvo (kkk)", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void inicializaComponentes() {
        etNome = (EditText) findViewById(R.id.etNome_editar);
        etArea = (EditText) findViewById(R.id.etAreaTotal_editar);
        cbElevador = (CheckBox) findViewById(R.id.cbElevador_editar);
        spinner = (Spinner) findViewById(R.id.spQtdAps_editar);
        btEditar = (Button) findViewById(R.id.btEditar_editar);

        dao = new CondoDAO(getApplicationContext());

        Bundle bundle= getIntent().getExtras();
        condominio = (Condominio) bundle.getSerializable("condo");

        posicao = condominio.getPosicao();
        id = condominio.getId();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.aps, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}
