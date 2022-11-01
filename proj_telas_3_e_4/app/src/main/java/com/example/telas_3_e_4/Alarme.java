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


public class Alarme extends AppCompatActivity {

    private Button  botaoSairTAlarme;
    private EditText editorSensorPresenca, editorSensorBuzina, editorSensorUltrassom, editorSensorLedAmarelo, editorSensorLedVermelho;
    private TextView txtHistoricoSMSAlarme;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarme);


        // Iniatilize and assign variable
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigation);

        botaoSairTAlarme = (Button) findViewById(R.id.btnSairTAlarme);
        editorSensorPresenca = (EditText) findViewById(R.id.edtTextSensorPresencaTAlarme);
        editorSensorBuzina = (EditText) findViewById(R.id.edtTextSensorBuzinaTAlarme);
        editorSensorUltrassom = (EditText) findViewById(R.id.edtTextSensorUltrassomTAlarme);
        editorSensorLedAmarelo = (EditText) findViewById(R.id.edtTextSensorLedAmareloTAlarme);
        editorSensorLedVermelho = (EditText) findViewById(R.id.edtTextSensorLedVermelhoTAlarme);
        txtHistoricoSMSAlarme = (TextView) findViewById(R.id.txtSMSTAlarme);

        //Set Alarm selected
        bottomNavigationView.setSelectedItemId(R.id.alarm);

        botaoSairTAlarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Alarme.this, "Você saiu do sistema!", Toast.LENGTH_SHORT).show();
                Intent intentSairTAlarme = new Intent(getApplicationContext(), Deslogar.class);
                startActivity(intentSairTAlarme);
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
                        //startActivity(new Intent(getApplicationContext(), Alarme.class));
                        //overridePendingTransition(0,0);
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