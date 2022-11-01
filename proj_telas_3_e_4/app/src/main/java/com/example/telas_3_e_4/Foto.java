package com.example.telas_3_e_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Foto extends AppCompatActivity {

    private Button botaoSairCamera;
    private EditText editorNomeFotoCameraTCamera, editorSensorCameraTCamera;
    private TextView textoListaHistoricoFotos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // Iniatilize and assign variable
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigation);


        botaoSairCamera = (Button) findViewById(R.id.btnSairTCamera);
        editorNomeFotoCameraTCamera = (EditText) findViewById(R.id.edtTextNomeFotoTCamera);
        editorSensorCameraTCamera = (EditText) findViewById(R.id.edtTextSensorCameraTCamera);
        textoListaHistoricoFotos = (TextView) findViewById(R.id.txtHistoricoFotoTCamera);

        //Set Câmera selected
        bottomNavigationView.setSelectedItemId(R.id.fotos_esp);


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
                        //startActivity(new Intent(getApplicationContext(), Camera.class));
                        //overridePendingTransition(0,0);
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