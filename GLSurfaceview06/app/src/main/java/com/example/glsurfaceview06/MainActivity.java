package com.example.glsurfaceview06;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView glvista;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rl = findViewById(R.id.relativeLayout);
        glvista = new MiGLSurface(this);
        rl.addView(glvista);
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