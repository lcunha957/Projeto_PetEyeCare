package com.example.peteyecareapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.peteyecareapp.R;

public class LogarActivity extends AppCompatActivity {

  private EditText edtTextEmail;
  private EditText edtTextSenha;
  private Button btnEntrarLogin;
  private Button  btnCadastrarLogin;
  private Button btnEsqueceuASenhaLogin;
  private Button btnLimparLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telalogar);

        edtTextEmail = (EditText) findViewById(R.id.edtTextEmail);
        edtTextSenha = (EditText) findViewById(R.id.edtTextSenha);
        btnEntrarLogin = (Button) findViewById(R.id.btnEntrarLogin);
        btnCadastrarLogin = (Button) findViewById(R.id.btnCadastrarLogin);
        btnEsqueceuASenhaLogin = (Button) findViewById(R.id.btnEsqueceuASenhaLogin);
        btnLimparLogin = (Button) findViewById(R.id.btnLimparLogin);


    }
}