package com.example.root.provaremorzinho.SQLite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.lang.reflect.Array;

public class CadastroActivity extends AppCompatActivity {
    private static final String TAG = "erro";


    private Button btCadastrar;
    private EditText etNome, etArea;
    private CheckBox cbElevador;
    private Spinner spinner;
    private Condominio condominio;

    private CondoDAO dao;

    private String nome, area;
    private String elevador = "";
    private String aps = "";

    private int posicao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializaComponentes();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
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


                condominio = new Condominio(nome, elevador, aps, area, posicao);
                Intent intent = new Intent(CadastroActivity.this, Lista.class);
                boolean salvou = dao.salvar(condominio);
                if (salvou) {
                    Toast.makeText(CadastroActivity.this, "Salvo", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(CadastroActivity.this, "Não salvo (kkk)", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void inicializaComponentes() {
        etNome = (EditText) findViewById(R.id.etNome);
        etArea = (EditText) findViewById(R.id.etAreaTotal);
        cbElevador = (CheckBox) findViewById(R.id.cbElevador);
        spinner = (Spinner) findViewById(R.id.spQtdAps);
        btCadastrar = (Button) findViewById(R.id.btCadastra_cadastro);

        dao = new CondoDAO(getApplicationContext());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.aps, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}
