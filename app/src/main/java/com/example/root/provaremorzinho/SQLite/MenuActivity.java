package com.example.root.provaremorzinho.SQLite;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.root.provaremorzinho.R;

public class MenuActivity extends ListActivity{
    private Button btCadastra, btEdita;
    private String[] botoes = {"cadastrar", "editar", "buscar", "excluir"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, botoes);
        super.setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        if(position == 0) {
            Intent intent = new Intent(MenuActivity.this, CadastroActivity.class);
            startActivity(intent);
        }
        else if(position == 1) {
            Intent intent = new Intent(MenuActivity.this, Lista.class);
            startActivity(intent);
        }
    }

}
