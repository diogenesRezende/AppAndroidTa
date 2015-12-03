package com.example.aluno0211.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
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
import java.util.List;

import model.ProdutoDAO;
import model.Produtos;

public class TruitonListFragment extends ListFragment{
    int fragNum;
    String arr[] = { "Produto 1", "Produto 2", "Produto 3", "Produto 4", "Produto 5" };
    String desc[] = { "Desc Produto 1", "Desc Produto 2", "Desc Produto 3", "Desc Produto 4", "Desc Produto 5" };
    //ProdutoDAO pDAO = new ProdutoDAO(TruitonListFragment.this.getContext());
    //List<Produtos> list = pDAO.getAllProdutos();
    //List<String> produtos = new ArrayList<>();

    /*private void createList(){
        for (int i = 0; i < list.size(); i++) {
            produtos.add((list.get(i).getNome()+" - "+list.get(i).getPreco()));
        }
    }*/

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
        ((TextView) tv).setText("Truiton Fragment #" + fragNum);
        return layoutView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, arr));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, final long id) {
        Log.i("Produto: ", "Item clicked: " + id);
        final long id2 = id;
        new AlertDialog.Builder(this.getContext())
                .setTitle("Confirmação de compra")
                .setMessage("Detalhes do produto:")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Integer i = (int) (long) id2;
                        Log.i("YES ", "Item clicked: " + id2);
                        Log.i("DESCRIPTION ", "Item clicked: " + desc[i]);
                        /*ClasseDiogenes cD = new ..;
                        if(cD.conecta(id))? deu certo : errado;*/
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