package com.example.root.provaremorzinho.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 13/06/17.
 */

public class SQLite extends SQLiteOpenHelper {

    private static SQLite instance;

    private final static String NOME_BANCO = "sqlitedb";
    private final static int versao = 1;

    private SQLite(Context context) {
        super(context, NOME_BANCO, null, versao);
    }

    public static SQLite getInstance(Context context) {
        if(instance == null){
            instance = new SQLite(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CondoDAO.CRIAR_TABELA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
