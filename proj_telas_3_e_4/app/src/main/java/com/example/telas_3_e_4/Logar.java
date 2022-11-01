package com.example.telas_3_e_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;



public class Logar extends AppCompatActivity {

    private Button botaoEntrarLogin, botaoCadastrarLogin, botaoLimparLogin;
    private ImageView imagemlogotipo;
    private EditText editTextEmail, editTextSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // declaração dos componentes visuais
        botaoEntrarLogin = (Button) findViewById(R.id.btnEntrarLogin);
        botaoCadastrarLogin = (Button) findViewById(R.id.btnCadastrarLogin);
        botaoLimparLogin = (Button) findViewById(R.id.btnLimparLogin);
        imagemlogotipo = (ImageView) findViewById(R.id.imageViewlogotipo);
        editTextEmail = (EditText) findViewById(R.id.edtTextEmail);
        editTextSenha = (EditText) findViewById(R.id.edtTextSenha);

        //quando chamar o botão de login mostra mensagem de usuário logado
        botaoEntrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Logar.this, "Usuário logado!", Toast.LENGTH_SHORT).show();
            }
        });

        // TO DO: autenticar via firebase authenticate


        // TO DO: fazer padrão MVC para linkar com o SQL Server

        // pegar código com o Leandro para crud

        // quando chamar o botão de cadastrar vai pra tela de formulário (classe Conta)
        botaoCadastrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Logar.this, "Você foi redirecionado ao nosso cadastro!", Toast.LENGTH_SHORT).show();
                Intent intentCadastrarFormulario = new Intent(getApplicationContext(), Conta.class);
                startActivity(intentCadastrarFormulario);
            }
        });

        // quando chamar o botão de limpar os campos serão resetados
        botaoLimparLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Logar.this, "Os dados foram limpos com sucesso!", Toast.LENGTH_SHORT).show();
                editTextEmail.setText("");
                editTextSenha.setText("");
            }
        });

    }
}