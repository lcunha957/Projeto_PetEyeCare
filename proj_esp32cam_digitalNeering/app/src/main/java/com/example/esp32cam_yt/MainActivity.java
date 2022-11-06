package com.example.esp32cam_yt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button btnip;
    private EditText edit_ip_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnip = (Button) findViewById(R.id.btnip);
        edit_ip_address = (EditText) findViewById(R.id.edit_ip_address);

    }
}