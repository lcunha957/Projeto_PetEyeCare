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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Gas extends AppCompatActivity {

    private Button  botaoSairTGas;
    private EditText editorSensorGas, editorSensorBuzinaTGas, editorValorGasTGas, editorSensorLedAmareloTGas, editorSensorLedVermelhoTGas;
    private TextView txtHistoricoSMSTGas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);


        // Iniatilize and assign variable
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigation);

        botaoSairTGas = (Button) findViewById(R.id.btnSairTGas);
        editorSensorGas = (EditText) findViewById(R.id.edtTextSensorGasTGas);
        editorSensorBuzinaTGas = (EditText) findViewById(R.id.edtTextSensorBuzinaTGás);
        editorValorGasTGas = (EditText) findViewById(R.id.edtTextValorGasTGas);
        editorSensorLedAmareloTGas = (EditText) findViewById(R.id.edtTextSensorLedAmareloTGas);
        editorSensorLedVermelhoTGas = (EditText) findViewById(R.id.edtTextSensorLedVermelhoTGas);
        txtHistoricoSMSTGas = (TextView) findViewById(R.id.txtSMSTGas);

        //Set Gás selected
        bottomNavigationView.setSelectedItemId(R.id.gas);


        botaoSairTGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Gas.this, "Você saiu do sistema!", Toast.LENGTH_SHORT).show();
                Intent intentSairTGas = new Intent(getApplicationContext(), Deslogar.class);
                startActivity(intentSairTGas);
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
                        //startActivity(new Intent(getApplicationContext(), Gas.class));
                        //overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

    }
}