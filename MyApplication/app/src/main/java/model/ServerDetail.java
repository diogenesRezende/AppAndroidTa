package model;

import java.util.HashMap;

/**
 * Created by diogenes on 04/12/15.
 */
public class ServerDetail extends HashMap {
    private int idProduto;
    private int qtdeEstoque;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(int qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }
}
