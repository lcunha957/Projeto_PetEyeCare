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
import android.widget.Toast;

public class Deslogar extends AppCompatActivity {

    private Button botaoSairDeslogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deslogar);


        botaoSairDeslogar = (Button) findViewById(R.id.btnSairDeslogar);

        // TO DO: confirmar saída via Firebase Authenticate

        // quando chamar o botão de sair, mostra a mensagem que saiu
        botaoSairDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Deslogar.this, "Usuário deslogado!", Toast.LENGTH_SHORT).show();
                Intent intentLogarAposDeslogue = new Intent(getApplicationContext(), Logar.class);
                startActivity(intentLogarAposDeslogue);

            }
        });

        // se quiser entrar via botão de entrada, vai passar a classe de logar
    }
}