package com.example.nodemcu_adaptado_esp32cam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnLed1, btnLed2, btnLed3, btnTodos;
    private TextView txtLdr;
    private EditText txtResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnLed1 = (Button) findViewById(R.id.btnLed1);
        btnLed2 = (Button) findViewById(R.id.btnLed2);
        btnLed3 = (Button) findViewById(R.id.btnLed3);
        btnTodos = (Button) findViewById(R.id.btnTodos);
        txtLdr = (TextView) findViewById(R.id.txtLdr);
        txtResultado =(EditText) findViewById(R.id.txtResultado);


    }
}