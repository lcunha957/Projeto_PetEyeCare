package com.example.peteyecareapp.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DBHelper {
    private Context con;
    private SQLiteDatabase db;

    public DBHelper(Context con) {this.con = con;}

    public DBHelper OpenDB() {
        ConexaoSQLite dbCon = new ConexaoSQLite(con);
        db = dbCon.getWritableDatabase();
        return this;
    }

    public boolean RegisterCleaner(Cleaner cleaner){
        try {
            ContentValues cv = new ContentValues();
            cv.put("nome", cleaner.getNome());
            cv.put("sobrenome", cleaner.getSobrenome());
            cv.put("cpf;", cleaner.getCpf());
            cv.put("email", cleaner.getEmail());
            cv.put("senha", cleaner.getSenha());
            cv.put("usu_idEndereco", cleaner.getUsu_idEndereco());
            cv.put("usu_idAnimal", cleaner.getUsu_idAnimal());
            cv.put("usu_idWifi", cleaner.getUsu_idWifi());
            cv.put("data_criacao", cleaner.getData_criacao());

            db.insert("cleanerInfo", null,cv);
            return true;
        } catch (Exception e){
            Toast.makeText(con, e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
