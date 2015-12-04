package model;

import android.provider.BaseColumns;

/**
 * Created by Aluno0211 on 24/11/2015.
 */
public class ProdutoContract {

    public static final String DB_NAME = "produto.db";
    public static final int DB_VERSION = 3;

    public static final String PRODUTO_TABLE = "PRODUTO";
    public static final String[] COLUMN_NAMES_P = {
            ColumnP.ID, ColumnP.NOME, ColumnP.PRECO
    };

    public class ColumnP{
        public static final String ID = BaseColumns._ID;
        public static final String NOME = "NOME";
        public static final String PRECO = "PRECO";
    }

    public static final String DETALHES_TABLE = "DETALHES";
    public static final String[] COLUMN_NAMES_D = {
            ColumnD.ID, ColumnD.DETALHE
    };

    public class ColumnD{
        public static final String ID = BaseColumns._ID;
        public static final String DETALHE = "DETALHE";
    }
}
