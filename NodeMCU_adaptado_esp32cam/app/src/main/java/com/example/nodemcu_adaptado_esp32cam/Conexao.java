package com.example.nodemcu_adaptado_esp32cam;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Conexao {

    public static String getDados(String urlUsuario){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlUsuario)
                .build();

        try{
            Response response = client.newCall(request).execute();
            return  response.body().string();
        } catch (IOException erro) {
            return erro.toString();
        }

    }
}
