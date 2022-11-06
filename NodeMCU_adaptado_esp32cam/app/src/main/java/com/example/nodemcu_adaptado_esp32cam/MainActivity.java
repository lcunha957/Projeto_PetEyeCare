package com.example.nodemcu_adaptado_esp32cam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnLed1, btnLed2, btnLed3, btnTodos;
    private TextView txtLdr;
    private EditText txtResultado;

    // preciso da Handle com processamento separado que interaja com a interface gráfica:
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnLed1 = (Button) findViewById(R.id.btnLed1);
        btnLed2 = (Button) findViewById(R.id.btnLed2);
        btnLed3 = (Button) findViewById(R.id.btnLed3);
        btnTodos = (Button) findViewById(R.id.btnTodos);
        txtLdr = (TextView) findViewById(R.id.txtLdr);
        txtResultado = (EditText) findViewById(R.id.txtResultado);

        //inicia a runnable imediatamente
        handler.postDelayed(atualizaStatus, 0);

        btnLed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicita("led1");

            }
        });

        btnLed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicita("led2");

            }
        });

        btnLed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicita("led3");

            }
        });

        btnTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicita("todos");

            }
        });

    }


    // conexão externa, deixar pra 5 ou 7s, ele muda a posição do potenciomero pra saber que está indo
    private Runnable atualizaStatus = new Runnable() {
        @Override
        public void run() {
        solicita("");
        handler.postDelayed(this, 2000); //a cada 2s carrega o app de novo
        }
    };

    public void solicita(String comando) {
        // ver se tem internet no celular
        ConnectivityManager connManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // url que a ESP32 te forneceu no Monitor Serial que é o ip...
            String url = "http://192.168.10.150/";

            new SolicitaDados().execute(url + comando);
        } else {
            Toast.makeText(MainActivity.this, "Nenhuma conexão de internet foi encontrada!", Toast.LENGTH_LONG).show();
        }
    }



    private class SolicitaDados extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            return Conexao.getDados(url[0]);
        }

        @Override
        protected void onPostExecute(String resultado) {
            if (resultado != null) {
                txtResultado.setText(resultado);
                if (resultado.contains("l1on")) {
                    //btnLed1.setText("LED 1 = LIGADO");
                    // Conforme o resultado muda a imagem
                    btnLed1.setBackgroundResource(R.drawable.img_on);
                } else if (resultado.contains("l1of")) {
                    //btnLed1.setText("LED 1 = DESLIGADO");
                    btnLed1.setBackgroundResource(R.drawable.img_off);
                }
                if (resultado.contains("l2on")) {
                    //btnLed2.setText("LED 2 = LIGADO");
                    btnLed2.setBackgroundResource(R.drawable.img_on);
                } else if (resultado.contains("l2of")) {
                    //btnLed2.setText("LED 2 = DESLIGADO");
                    btnLed2.setBackgroundResource(R.drawable.img_off);
                }
                if (resultado.contains("l3on")) {
                    //btnLed3.setText("LED 3 = LIGADO");
                    btnLed3.setBackgroundResource(R.drawable.img_on);
                } else if (resultado.contains("l3of")) {
                    //btnLed3.setText("LED 3 = DESLIGADO");
                    btnLed3.setBackgroundResource(R.drawable.img_off);
                }
                // quebrando o resultado nas vírgulas:
                String[] dados_recebidos = resultado.split(",");
                // quebrando o resultado no índice 3: para cada led vai dar um valor diferente
                txtLdr.setText(dados_recebidos[3]);
            } else {
                Toast.makeText(MainActivity.this, "Ocorreu um erro", Toast.LENGTH_LONG).show();
            }
        }
    }
}