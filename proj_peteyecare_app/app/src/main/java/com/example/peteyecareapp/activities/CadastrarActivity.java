package com.example.peteyecareapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peteyecareapp.R;
import com.example.peteyecareapp.dbHelper.Cleaner;
import com.example.peteyecareapp.dbHelper.DBHelper;

public class CadastrarActivity extends AppCompatActivity {

    EditText edtTextCPF, edtTextNome, edtTextSobrenome, edtTextCelular,edtTextEmail,edtTextSenha,edtTextSSID,edtTextSenhaWifi,edtTextCEP,edtTextNumResid,edtTextComplemento,edtTextTipoAnimal,edtTextSobrenomeAnimal;

    private DBHelper dbHelper;
    Button ButtonRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        dbHelper= new DBHelper(this);
        dbHelper.OpenDB();

        edtTextCPF = findViewById(R.id.edtTextCPF);
        edtTextNome = findViewById(R.id.edtTextNome);
        edtTextSobrenome = findViewById(R.id.edtTextSobrenome);
        edtTextCelular = findViewById(R.id.edtTextCelular);
        edtTextEmail = findViewById(R.id.edtTextEmail);
        edtTextSenha = findViewById(R.id.edtTextSenha);
        edtTextSSID = findViewById(R.id.edtTextSSID);
        edtTextSenhaWifi = findViewById(R.id.edtTextSenhaWifi);
        edtTextCEP = findViewById(R.id.edtTextCEP);
        edtTextNumResid = findViewById(R.id.edtTextNumResid);
        edtTextComplemento = findViewById(R.id.edtTextComplemento);
        edtTextTipoAnimal = findViewById(R.id.edtTextTipoAnimal);
        edtTextSobrenomeAnimal = findViewById(R.id.edtTextSobrenomeAnimal);
        ButtonRegistrar = findViewById(R.id.btnFazerCadastro);

        ButtonRegistrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view){
               if(edtTextCPF.getText().toString().isEmpty() || edtTextNome.getText().toString().isEmpty() || edtTextSobrenome.getText().toString().isEmpty() || edtTextCelular.getText().toString().isEmpty() || edtTextCelular.getText().toString().isEmpty() || edtTextEmail.getText().toString().isEmpty() || edtTextSenha.getText().toString().isEmpty() || edtTextSSID.getText().toString().isEmpty() || edtTextSenhaWifi.getText().toString().isEmpty() || edtTextCEP.getText().toString().isEmpty() || edtTextNumResid.getText().toString().isEmpty() || edtTextComplemento.getText().toString().isEmpty() || edtTextTipoAnimal.getText().toString().isEmpty() || edtTextSobrenomeAnimal.getText().toString().isEmpty()){
                   Toast.makeText(getApplicationContext(),"Não deu certo ", Toast.LENGTH_LONG).show();
               }
               else if(!edtTextSenha.getText().toString().equals(edtTextSenha.getText().toString())){
                   Toast.makeText(getApplicationContext(), "Coloque sua senha", Toast.LENGTH_LONG).show();
               }
               else{
                   Cleaner cleaner = new Cleaner(edtTextCPF.getText().toString(),edtTextNome.getText().toString(),edtTextSobrenome.getText().toString(),edtTextCelular.getText().toString(),edtTextEmail.getText().toString(),edtTextSenha.getText().toString(),edtTextSSID.getText().toString(),edtTextSenhaWifi.getText().toString(),edtTextCEP.getText().toString(),edtTextNumResid.getText().toString(),edtTextComplemento.getText().toString(),edtTextTipoAnimal.getText().toString(),edtTextSobrenomeAnimal.getText().toString());
                   if(dbHelper.RegisterCleaner(cleaner)){
                        Toast.makeText(getApplicationContext(),"registrado com sucesso", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(CadastrarActivity.this, LogarActivity.class);
                        startActivity(intent);
                   }
                   else{
                       Toast.makeText(getApplicationContext(),"Usuario registarado", Toast.LENGTH_LONG).show();
                   }
               }
           }
        });
    }

    public void Login(View view){
        Intent intent = new Intent(this, LogarActivity.class);
        startActivity(intent);
    }
}