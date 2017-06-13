package com.example.root.provaremorzinho.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.root.provaremorzinho.POJO.Condominio;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 13/06/17.
 */

public class CondoDAO {
    private SQLiteDatabase db;

    public static final String NOME_TABELA = "condominios";
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String ELEVADOR = "elevador";
    public static final String QUANTIDADE= "quantidade";
    public static final String AREA = "area";
    public static final String CRIAR_TABELA = "CREATE TABLE " + NOME_TABELA + "("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
            ""+NOME + " TEXT NOT NULL, " + ELEVADOR + " TEXT NOT NULL, " + QUANTIDADE + " TEXT NOT NULL, "+ AREA +" TEXT NOT NULL)";


    public CondoDAO(Context context){
        db = SQLite.getInstance(context).getWritableDatabase();
    }

    public boolean salvar(Condominio condo){
        long i = db.insert(NOME_TABELA, null, condoToValues(condo));

        // O metodo insert retorna -1 quando der merda na execucão
        if(i == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deletar(Condominio condo) {
        String where = ID + "=?";

        String[] whereArgs = {
                condo.getId().toString()
        };

        long i = db.delete(NOME_TABELA, where, whereArgs);

        if(i == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean editar(Condominio condo){
        String where = ID + " =?";

        String[] whereArgs = {
                condo.getId().toString()
        };
        long i = db.update(NOME_TABELA, condoToValues(condo), where, whereArgs);

        if(i == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Condominio> listar(){
        ArrayList<Condominio> listaCondominios = new ArrayList<Condominio>();
        String sqlBusca = "select * from " + NOME_TABELA;
        Log.d(TAG, "listar: " +sqlBusca);
        Cursor cursor = db.rawQuery(sqlBusca, null);

        Condominio condo;

        int indiceId = cursor.getColumnIndex(ID);
        int indiceNome = cursor.getColumnIndex(NOME);
        int indiceQuantidade = cursor.getColumnIndex(QUANTIDADE);
        int indiceElevador = cursor.getColumnIndex(ELEVADOR);
        int indiceArea = cursor.getColumnIndex(AREA);

        while(cursor.moveToNext()) {
            condo = new Condominio();

            condo.setId(cursor.getString(indiceId));
            condo.setNome(cursor.getString(indiceNome));
            condo.setQuantidadeAps(cursor.getString(indiceQuantidade));
            condo.setElevador(cursor.getString(indiceElevador));
            condo.setArea(cursor.getString(indiceArea));

            listaCondominios.add(condo);
        }
         return listaCondominios;
    }

    private ContentValues condoToValues(Condominio condo){
        ContentValues contentvalues = new ContentValues();

        contentvalues.put(NOME, condo.getNome());
        contentvalues.put(QUANTIDADE, condo.getQuantidadeAps());
        contentvalues.put(ELEVADOR, condo.getElevador());
        contentvalues.put(AREA, condo.getArea());
        return  contentvalues;

    }
}
