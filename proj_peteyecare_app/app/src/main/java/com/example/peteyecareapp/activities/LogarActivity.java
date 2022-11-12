package com.example.peteyecareapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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


        btnEntrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogar = new Intent(LogarActivity.this, MonitoramentoActivity.class);
                Toast.makeText(LogarActivity.this, "Veja os nossos sensores na tela de monitoramento", Toast.LENGTH_LONG).show();
                startActivity(intentLogar);
            }
        });

        btnEsqueceuASenhaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent esqueceuASenha = new Intent(LogarActivity.this, RestauraSenhaActivity.class);
                Toast.makeText(LogarActivity.this, "Veja o processo de restaurar a senha", Toast.LENGTH_LONG).show();
                startActivity(esqueceuASenha);
            }
        });

      btnCadastrarLogin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent cadastroulogin = new Intent(LogarActivity.this, CadastrarActivity.class);
              Toast.makeText(LogarActivity.this, "Realize o seu cadastro na tela de cadastro", Toast.LENGTH_SHORT).show();
              startActivity(cadastroulogin);
          }
      });

      btnLimparLogin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Toast.makeText(LogarActivity.this, "Os campos do login serão limpos", Toast.LENGTH_LONG).show();
              edtTextEmail.setText("");
              edtTextSenha.setText("");
          }
      });

    }
}