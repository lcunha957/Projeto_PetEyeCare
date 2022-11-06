package com.example.enviandosms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EnviarSMS extends AppCompatActivity {

    private Button btnEnviarSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_sms);

    }

    public void EnviarSMS(View v){
        SmsManager smsManager = SmsManager.getDefault();
        // parâmetros: ddd+ celular que quer enviar
        smsManager.sendTextMessage("019992460030", null,"Programadora_Lunara enviando sms via Android Studio", null, null);
        Toast.makeText(EnviarSMS.this, "SMS enviado com sucesso", Toast.LENGTH_LONG).show();
    }
}