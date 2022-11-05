package com.example.peteyecareapp.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexaoSQLite extends SQLiteOpenHelper {

    private static ConexaoSQLite INSTANCIA_CONEXAO;
    private static final int VERSAO_DB = 1;
    private static final String NOME_DB = "PetEyeCare";

    public ConexaoSQLite(@Nullable Context context) {
        super(context, NOME_DB, null, VERSAO_DB);

    }


    public static ConexaoSQLite getInstancia(Context context) {
        if (INSTANCIA_CONEXAO == null) {
            INSTANCIA_CONEXAO = new ConexaoSQLite(context);
        }
        return INSTANCIA_CONEXAO;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
// tabela usuario
        String sqlTabelaUsuario =
                "CREATE TABLE IF NOT EXISTS usuario" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "cpf TEXT(14) UNIQUE," +
                        "nome TEXT(20)," +
                        "sobrenome TEXT(30)," +
                        "email TEXT(50)," +
                        "senha TEXT(5)," +
                        "celular INTEGER(11)," +
                        "usu_idEndereco INTEGER(2)," +
                        "usu_idAnimal INTEGER(2)," +
                        "usu_idWifi INTEGER(2)," +
                        "data_criacao INTEGER" +
                        ")";
        db.execSQL(sqlTabelaUsuario);

        // tabela wifi
        String sqlTabelaWifi =
                "CREATE TABLE IF NOT EXISTS wifi" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "ssid TEXT(20)," +
                        "password_wifi TEXT(20)," +
                        "data_criacao INTEGER" +
                        ")";
        db.execSQL(sqlTabelaWifi);

        // tabela animal
        String sqlTabelaAnimal =
                "CREATE TABLE IF NOT EXISTS animal" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "tipo TEXT(8)," +
                        "nomeAnimal TEXT(20)," +
                        "sobrenomeAnimal TEXT(20)," +
                        "animal_idEndereco INTEGER(2)," +
                        "animal_idUsuario INTEGER(2)," +
                        "data_criacao INTEGER" +
                        ")";

        db.execSQL(sqlTabelaAnimal);

        //tabela endereço
        String sqlTabelaEndereco =
                "CREATE TABLE IF NOT EXISTS endereco" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "endereco_idUsuario INTEGER(2)," +
                        "cep INTEGER(2) UNIQUE," +
                        "numeroResidencia INTEGER(3)," +
                        "complemento TEXT(3)," +
                        "data_criacao INTEGER" +
                        ")";
        db.execSQL(sqlTabelaEndereco);


        // tabela imagens
        String sqlTabelaImagens =
                "CREATE TABLE IF NOT EXISTS imagens" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "imagens_idAnimal INTEGER(2)," +
                        "url TEXT(200)," +
                        "data_criacao INTEGER" +
                        ")";
        db.execSQL(sqlTabelaImagens);


        // tabela historicoalarme
        String sqlTabelaHistoricoAlarme =
                "CREATE TABLE IF NOT EXISTS historicoalarme" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "histam_idUsuario INTEGER(3), " +
                        "mensagem TEXT(500), " +
                        "data_criacao INTEGER" +
                        ")";

        db.execSQL(sqlTabelaHistoricoAlarme);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
