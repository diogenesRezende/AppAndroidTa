package model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.example.aluno0211.myapplication.MainActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Aluno0211 on 24/11/2015.
 */
public class ProdutoDAO extends ProdutoDBHelper {
    public ProdutoDAO(Context context) {
        super(context);
    }

    public void salvarP(Produtos produto){

        ContentValues values = new ContentValues();
        values.put(ProdutoContract.ColumnP.NOME, produto.getNome());
        values.put(ProdutoContract.ColumnP.PRECO, produto.getPreco());

        SQLiteDatabase db = super.getWritableDatabase();
        db.insert(ProdutoContract.PRODUTO_TABLE, null, values);
    }

    public Cursor buscarP(){

        SQLiteQueryBuilder qB = new SQLiteQueryBuilder();
        qB.setTables(ProdutoContract.PRODUTO_TABLE);
        SQLiteDatabase dB = this.getReadableDatabase();

        Cursor cursor = qB.query(dB, ProdutoContract.COLUMN_NAMES_P, null, null, null, null, null);

        return cursor;
    }

    public Cursor buscarD(){

        SQLiteQueryBuilder qB = new SQLiteQueryBuilder();
        qB.setTables(ProdutoContract.DETALHES_TABLE);
        SQLiteDatabase dB = this.getReadableDatabase();

        Cursor cursor = qB.query(dB, ProdutoContract.COLUMN_NAMES_D, null, null, null, null, null);

        return cursor;
    }

    public List<Produtos> getAllProdutos(){
        Cursor cursor = buscarP();

        List<Produtos> produtos = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                Produtos p = new Produtos();
                p.setNome(cursor.getString(1));
                p.setPreco(cursor.getFloat(2));

                produtos.add(p);
            }while(cursor.moveToNext());
        }

        cursor.close();

        return produtos;
    }

    public List<Detalhes> getAllDetalhes(){
        Cursor cursor = buscarD();

        List<Detalhes> detalhes = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                Detalhes d = new Detalhes();
                d.setDetalhes(cursor.getString(1));

                detalhes.add(d);
            }while(cursor.moveToNext());
        }

        cursor.close();

        return detalhes;
    }

    public List<Detalhes> getDetalhes(Long id){
        SQLiteQueryBuilder qB = new SQLiteQueryBuilder();

        String selectQuery = "SELECT * FROM DETALHES_TABLE WHERE ID =" +id;

        SQLiteDatabase dB = this.getReadableDatabase();
        Cursor cursor = qB.query(dB, ProdutoContract.COLUMN_NAMES_D, null, null, null, null, null);
        cursor.moveToFirst();
        List<Detalhes> detalhes = new ArrayList<>();

        Detalhes d = new Detalhes();
        d.setDetalhes(cursor.getString(1));

        detalhes.add(d);

        cursor.close();

        return detalhes;
    }
}
