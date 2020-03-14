package com.example.sonido04;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ImageButton play;
    private ImageButton grabar;
    private ImageButton pausa;
    private MediaRecorder miGrabadora;
    private String salida = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.play);
        grabar = findViewById(R.id.grabar);
        pausa = findViewById(R.id.pausa);
        salida = Environment.getExternalStorageDirectory().getAbsolutePath() + "/grabado.3gp";
        miGrabadora = new MediaRecorder();
        miGrabadora.setAudioSource(MediaRecorder.AudioSource.MIC);
        miGrabadora.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        miGrabadora.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        miGrabadora.setOutputFile(salida);

        grabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    miGrabadora.prepare();
                    miGrabadora.start();
                }catch (IllegalStateException | IOException e){
                    e.printStackTrace();
                }
            }
        });

        pausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miGrabadora.stop();
                miGrabadora.release();
                miGrabadora = null;
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer repro = new MediaPlayer();
                try{
                    repro.setDataSource(salida);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    repro.prepare();
                }catch (IOException e) {
                    e.printStackTrace();
                }
                repro.start();
            }
        });

    }
}