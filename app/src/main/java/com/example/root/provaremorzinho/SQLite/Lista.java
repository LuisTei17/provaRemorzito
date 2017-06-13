package com.example.root.provaremorzinho.SQLite;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.root.provaremorzinho.DAO.CondoDAO;
import com.example.root.provaremorzinho.POJO.Condominio;
import com.example.root.provaremorzinho.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Lista extends ListActivity {
    ArrayList<Condominio> condos;
    CondoDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        condos = new ArrayList<>();
        dao = new CondoDAO(this);
        condos = dao.listar();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, condos);
        super.setListAdapter(adapter);

    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(Lista.this, EditaActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("condo", condos.get(position));
        intent.putExtras(args);
        startActivity(intent);
    }
}
