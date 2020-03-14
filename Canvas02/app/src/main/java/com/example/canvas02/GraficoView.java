package com.example.canvas02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

class GraficoView extends View implements Runnable{

    int x,y,ymax;
    boolean continuar = true;
    int dt = 10;
    int tiempo = 0;
    float velocidad = 0.5f;
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
    public void run() {
        while (continuar){
            tiempo += dt;
            y  += (int) (velocidad*dt);
            if (y>ymax)
                velocidad = -velocidad;
            if (y<0)
                velocidad = -velocidad;
            postInvalidate();
            try {
                Thread.sleep(dt);
            }catch(InterruptedException e){

            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        x = w/2;
        y = 0;
        ymax = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(paintFondo);
        canvas.drawCircle(x+150,y,30,paintParticula);
        canvas.drawText("Altura = "+y,50,50,paint);
        canvas.drawText("Tiempo = "+tiempo,50,90,paint);
    }
}
