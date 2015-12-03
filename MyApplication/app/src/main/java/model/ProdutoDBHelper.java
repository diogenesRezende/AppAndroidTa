package model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

/**
 * Created by Aluno0211 on 24/11/2015.
 */
public class ProdutoDBHelper extends SQLiteOpenHelper {
    public ProdutoDBHelper(Context context) {
        super(context, ProdutoContract.DB_NAME, null, ProdutoContract.DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUTO_TABLE = "Create Table " + ProdutoContract.PRODUTO_TABLE + " ("
                +ProdutoContract.ColumnP.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +ProdutoContract.ColumnP.NOME + " TEXT NOT NULL,"
                +ProdutoContract.ColumnP.PRECO + " DOUBLE NOT NULL"
                + " )";

        db.execSQL(CREATE_PRODUTO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = " DROP TABLE " + ProdutoContract.PRODUTO_TABLE;
        db.execSQL(drop);
        String CREATE_PRODUTO_TABLE = "Create Table " + ProdutoContract.PRODUTO_TABLE + " ("
                +ProdutoContract.ColumnP.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +ProdutoContract.ColumnP.NOME + " TEXT NOT NULL,"
                +ProdutoContract.ColumnP.PRECO + " DOUBLE NOT NULL"
                + " )";
        db.execSQL(CREATE_PRODUTO_TABLE);

        String CREATE_DETALHES_TABLE = "Create Table " + ProdutoContract.DETALHES_TABLE + " ("
                +ProdutoContract.ColumnD.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +ProdutoContract.ColumnD.DETALHE + " TEXT NOT NULL"
                + " )";
        db.execSQL(CREATE_PRODUTO_TABLE);

        // --------- Produto
        ContentValues c = new ContentValues();
        c.put(ProdutoContract.ColumnP.NOME, "Produto 1");
        c.put(ProdutoContract.ColumnP.PRECO, 10.0);

        SQLiteDatabase dB = super.getWritableDatabase();
        dB.insert(ProdutoContract.PRODUTO_TABLE, null, c);

        // ---------
        c = new ContentValues();
        c.put(ProdutoContract.ColumnP.NOME, "Produto 2");
        c.put(ProdutoContract.ColumnP.PRECO, 20.0);

        dB = super.getWritableDatabase();
        dB.insert(ProdutoContract.PRODUTO_TABLE, null, c);

        // ---------
        c = new ContentValues();
        c.put(ProdutoContract.ColumnP.NOME, "Produto 3");
        c.put(ProdutoContract.ColumnP.PRECO, 30.0);

        dB = super.getWritableDatabase();
        dB.insert(ProdutoContract.PRODUTO_TABLE, null, c);

        // ---------
        c = new ContentValues();
        c.put(ProdutoContract.ColumnP.NOME, "Produto 4");
        c.put(ProdutoContract.ColumnP.PRECO, 11.0);

        dB = super.getWritableDatabase();
        dB.insert(ProdutoContract.PRODUTO_TABLE, null, c);

        // ---------
        c = new ContentValues();
        c.put(ProdutoContract.ColumnP.NOME, "Produto 5");
        c.put(ProdutoContract.ColumnP.PRECO, 12.0);

        dB = super.getWritableDatabase();
        dB.insert(ProdutoContract.PRODUTO_TABLE, null, c);

        // --------- Detalhes
        c = new ContentValues();
        c.put(ProdutoContract.ColumnD.DETALHE, "Detalhes do produto 1");

        dB = super.getWritableDatabase();
        dB.insert(ProdutoContract.DETALHES_TABLE, null, c);

        // ---------
        c = new ContentValues();
        c.put(ProdutoContract.ColumnD.DETALHE, "Detalhes do produto 2");

        dB = super.getWritableDatabase();
        dB.insert(ProdutoContract.DETALHES_TABLE, null, c);

        // ---------
        c = new ContentValues();
        c.put(ProdutoContract.ColumnD.DETALHE, "Detalhes do produto 3");

        dB = super.getWritableDatabase();
        dB.insert(ProdutoContract.DETALHES_TABLE, null, c);

        // ---------
        c = new ContentValues();
        c.put(ProdutoContract.ColumnD.DETALHE, "Detalhes do produto 4");

        dB = super.getWritableDatabase();
        dB.insert(ProdutoContract.DETALHES_TABLE, null, c);

        // ---------
        c = new ContentValues();
        c.put(ProdutoContract.ColumnD.DETALHE, "Detalhes do produto 5");

        dB = super.getWritableDatabase();
        dB.insert(ProdutoContract.DETALHES_TABLE, null, c);
    }
}
