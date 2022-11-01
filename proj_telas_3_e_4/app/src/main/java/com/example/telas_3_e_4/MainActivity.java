package com.example.telas_3_e_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Button botaoSair, botaoSensorAlarme, botaoSensorGas, botaoSensorCamera;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Iniatilize and assign variable
            BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigation);


            botaoSair = (Button) findViewById(R.id.btnSair);
            botaoSensorAlarme = (Button) findViewById(R.id.btnSensorAlarme);
            botaoSensorGas = (Button) findViewById(R.id.btnSensorGas);
            botaoSensorCamera = (Button) findViewById(R.id.btnSensorCamera);

            //Set Alarm selected
            bottomNavigationView.setSelectedItemId(R.id.spy);

            botaoSair.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Você será redirecionado ao logout", Toast.LENGTH_SHORT).show();
                    Intent intentLogout = new Intent(getApplicationContext(), Deslogar.class);
                    startActivity(intentLogout);
                }
            });

            botaoSensorAlarme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Bem-vindo ao monitoramento do sensor de alarme!", Toast.LENGTH_SHORT).show();
                    Intent intentTelaAlarme = new Intent(getApplicationContext(), Alarme.class);
                    startActivity(intentTelaAlarme);

                }
            });

            botaoSensorGas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Bem-vindo ao monitoramento do sensor de gás!", Toast.LENGTH_SHORT).show();
                    Intent intentTelaGas = new Intent(getApplicationContext(),Gas.class);
                    startActivity(intentTelaGas);

                }
            });

            botaoSensorCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Bem-vindo ao monitoramento do sensor da câmera!", Toast.LENGTH_SHORT).show();
                    Intent intentTelaCamera = new Intent(getApplicationContext(),Gas.class);
                    startActivity(intentTelaCamera);

                }
            });

            //Perform item selected listener
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId())
                    {
                        case R.id.account:
                            startActivity(new Intent(getApplicationContext(), Conta.class));
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.spy:
                            //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            //overridePendingTransition(0,0);
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