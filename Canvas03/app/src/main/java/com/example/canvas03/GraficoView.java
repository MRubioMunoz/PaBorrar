package com.example.canvas03;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class GraficoView extends View implements Runnable {

    int x,y,ymax;
    boolean continuar = true;
    int dt = 1;
    int tiempo = 0;
    float velocidad = 0.5f;
    float energia;
    float aceleracion = 0.01f;
    Paint paintFondo, paintParticula,paint;

    public GraficoView(Context context){
        super(context);
        paintFondo = new Paint();
        paintParticula = new Paint();
        paint = new Paint();
        paintFondo.setColor(Color.WHITE);
        paintParticula.setColor(Color.RED);
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        x = w / 2;
        y = 0;
        ymax = h;
        energia = 0.5f * velocidad*velocidad-aceleracion*y;
    }

    @Override
    public void run() {
        while (continuar){
           // cambiarPosicion();
            cambiarPosicion2();
            postInvalidate();
            try {
                Thread.sleep(dt);
            }catch (InterruptedException e){

            }
        }
    }

    public void cambiarPosicion(){
        tiempo = tiempo+dt;
        velocidad = velocidad+aceleracion*dt;
        float cinetica = (float) ((Math.pow(velocidad,2))/2);
        y = (int) ((cinetica-energia)/aceleracion);
        if(y>ymax)
            velocidad = -Math.abs(velocidad);
        if (y<0)
            velocidad = -Math.abs(velocidad);
    }

    public void cambiarPosicion2(){
        tiempo += dt;
        y  = y + (int) (velocidad*dt);
        velocidad = velocidad+aceleracion*dt;
        if (y>ymax)
            velocidad = -velocidad;
        if (y<0)
            velocidad = -velocidad;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(paintFondo);
        canvas.drawCircle(x+150,y,30,paintParticula);
        canvas.drawText("Altura = "+y,50,50,paint);
        canvas.drawText("Tiempo = "+tiempo,50,90,paint);
        canvas.drawText("Velocidad = "+velocidad,50,130,paint);
    }
}
