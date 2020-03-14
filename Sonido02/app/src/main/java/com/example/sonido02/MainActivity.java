package com.example.sonido02;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton retroceder,iniciar,pausar, avanzar;
    private SeekBar duracion;
    private TextView tiempoCancion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retroceder = findViewById(R.id.retroceso);
        iniciar = findViewById(R.id.iniciar);
        pausar = findViewById(R.id.pausa);
        avanzar = findViewById(R.id.pasar);

        duracion = findViewById(R.id.seekBar);
        duracion.setClickable(false);
        tiempoCancion = findViewById(R.id.duracion);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.ezio);
        final double duracionTiempo = mediaPlayer.getDuration();
        duracion.setMax((int) duracionTiempo);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });


        pausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

    }
}
