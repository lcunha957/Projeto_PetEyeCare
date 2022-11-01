package com.example.telas_3_e_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Conta extends AppCompatActivity {

    private Button  botaoSairtconta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta);

        // Iniatilize and assign variable
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigation);


        botaoSairtconta = (Button) findViewById(R.id.btnSairConta);

        //Set Account selected
        bottomNavigationView.setSelectedItemId(R.id.account);

        botaoSairtconta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Conta.this, "Você será redirecionado ao logout", Toast.LENGTH_SHORT).show();
                Intent intentLogout = new Intent(getApplicationContext(), Deslogar.class);
                startActivity(intentLogout);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.account:
                        //startActivity(new Intent(getApplicationContext(), Conta.class));
                        //overridePendingTransition(0,0);
                        return true;
                    case R.id.spy:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.alarm:
                        startActivity(new Intent(getApplicationContext(), Alarme.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.fotos_esp:
                        startActivity(new Intent(getApplicationContext(), Foto.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.gas:
                        startActivity(new Intent(getApplicationContext(), Gas.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });


    }
}