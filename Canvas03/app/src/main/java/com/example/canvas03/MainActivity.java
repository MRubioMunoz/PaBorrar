package com.example.canvas03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    GraficoView dinamica;
    Thread hilo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dinamica = new GraficoView(this);
        setContentView(dinamica);

        hilo = new Thread(dinamica);
        hilo.start();
    }
}
