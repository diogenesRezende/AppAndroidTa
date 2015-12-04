package com.example.aluno0211.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import model.Detalhes;
import model.HttpUtilUnivas;
import model.ProdutoDAO;
import model.Produtos;
import model.ServerDetail;

public class TruitonListFragment extends ListFragment{
    int fragNum;
    //String arr[] = { "Produto 1", "Produto 2", "Produto 3", "Produto 4", "Produto 5" };
    //String desc[] = { "Desc Produto 1", "Desc Produto 2", "Desc Produto 3", "Desc Produto 4", "Desc Produto 5" };
    List<String> detalhes;

    static TruitonListFragment init(int val) {
        TruitonListFragment truitonList = new TruitonListFragment();

        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        truitonList.setArguments(args);

        return truitonList;
    }

    /**
     * Retrieving this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragNum = getArguments() != null ? getArguments().getInt("val") : 1;
    }

    /**
     * The Fragment's UI is a simple text view showing its instance number and
     * an associated list.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_pager_list,
                container, false);
        View tv = layoutView.findViewById(R.id.text);
        ((TextView) tv).setText("Venda de Produtos #" + fragNum);
        return layoutView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ProdutoDAO pDAO = new ProdutoDAO(this.getActivity());

        List<Produtos> list = pDAO.getAllProdutos();
        List<String> produtos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            produtos.add((list.get(i).getNome()+" - "+list.get(i).getPreco()));
        }

        List<Detalhes> list2 = pDAO.getAllDetalhes();
        detalhes = new ArrayList<>();
        for (int i = 0; i < list2.size(); i++) {
            detalhes.add(list2.get(i).getDetalhes());
        }

        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, produtos));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, final long id) {
        Log.i("Produto: ", "Item clicked: " + id);
        final long id2 = id;
        final Integer i = (int) (long) id2;
        HttpUtilUnivas task = new HttpUtilUnivas(this.getContext(),(i+1));

       ServerDetail resultTask = null;
        try {
            resultTask = task.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        final int qtdeEstoque = resultTask.getQtdeEstoque();


        Log.d(getTag(), "foi");
        final Context c = this.getContext();
        new AlertDialog.Builder(this.getContext())
                .setTitle("Confirmação de compra")
                .setMessage("Detalhes do produto: " + detalhes.get(i))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("YES ", "Item clicked: " + id2);
                        Log.i("DESCRIPTION ", "Item clicked: " + detalhes);
                        if (qtdeEstoque > 0) {
                            Log.i("Success: ", "qtdeEstoque: " + qtdeEstoque);
                            new AlertDialog.Builder(c)
                                    .setTitle("Confirmação de compra")
                                    .setMessage("Compra confirmada!")
                                    .setCancelable(false)
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        } else {
                            Log.i("Fail: ", "qtdeEstoque: " + qtdeEstoque);
                            new AlertDialog.Builder(c)
                                    .setTitle("Confirmação de compra")
                                    .setMessage("Produto em falta no estoque!")
                                    .setCancelable(false)
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("NO ", "Item clicked: " + id2);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}