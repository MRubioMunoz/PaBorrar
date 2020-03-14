package com.example.glsurfaceview7;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView glvista;
    RelativeLayout rl;
    private Button dr;
    private Button iz;
    private Button ar;
    private Button ab;
    private Button at;
    private Button de;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rl = findViewById(R.id.relativeLayout);
        glvista = new MiGLSurface(this);
        rl.addView(glvista);
        dr = findViewById(R.id.dr);
        iz = findViewById(R.id.iz);
        ar = findViewById(R.id.ar);
        ab = findViewById(R.id.ab);
        at = findViewById(R.id.at);
        de = findViewById(R.id.de);

        dr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiGLSurface.mueve(0);
            }
        });

        iz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiGLSurface.mueve(1);
            }
        });

        ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiGLSurface.mueve(2);
            }
        });

        ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiGLSurface.mueve(3);
            }
        });

        at.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiGLSurface.mueve(4);
            }
        });

        de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiGLSurface.mueve(5);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        glvista.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glvista.onResume();
    }
}
