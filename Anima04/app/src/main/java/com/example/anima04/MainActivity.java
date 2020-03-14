package com.example.anima04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView imagen;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        imagen = findViewById(R.id.imagen);
        layout = findViewById(R.id.layout);

        int ancho = imagen.getMaxWidth()/2;
        int alto = imagen.getMaxHeight()/2;

        float radioFinal = (float) Math.hypot(ancho,alto);
        Animator movimiento = ViewAnimationUtils.createCircularReveal(layout,ancho,alto,0,radioFinal);
        layout.setVisibility(View.INVISIBLE);
        movimiento.start();
    }
}
