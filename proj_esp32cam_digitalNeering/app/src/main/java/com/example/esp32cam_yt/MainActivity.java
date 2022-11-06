package com.example.esp32cam_yt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button enter_ip;
    private EditText ip;
    public static String ip_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enter_ip = (Button) findViewById(R.id.btnip);
        ip = (EditText) findViewById(R.id.edit_ip_address);

        enter_ip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ip_address = ip.getText().toString();
             Intent intent = new Intent(MainActivity.this, activity_2.class);
             startActivity(intent);
;            }
        });

    }
}