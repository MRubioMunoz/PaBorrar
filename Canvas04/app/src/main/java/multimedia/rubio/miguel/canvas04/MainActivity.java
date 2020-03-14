package multimedia.rubio.miguel.canvas04;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean continuar = true;
    float velocidad = 0.1f;
    float aceleracion = 0.001f;
    float energia;
    int dt = 1;
    int tiempo = 0;
    Thread hilo = null;
    GraficoView dinamica;
    Trayectoria trayectoria = new Trayectoria(100, 100);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dinamica = new GraficoView(this);
        setContentView(dinamica);
        dinamica.setOnClickListener(this);

        hilo = new Thread(dinamica);
        hilo.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        continuar = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        continuar = true;
        if (hilo == null) {
            hilo = new Thread(dinamica);
            hilo.start();
        }
    }

    @Override
    public void onClick(View v) {
        continuar = !continuar;
        if (continuar | hilo == null) {
            hilo = new Thread(dinamica);
            hilo.start();
        }
    }

    class GraficoView extends View implements Runnable {

        int x, y, xmin, ymin, ymax;
        float tangenteX, tangenteY;
        float tx, ty;
        Paint paintFondo, paintParticula, paint, paintCurva, paintVector;
        Path path;
        String control;
        boolean cambio = true;

        public GraficoView(Context context) {
            super(context);
            paintFondo = new Paint();
            paintParticula = new Paint();
            paint = new Paint();
            paintFondo.setColor(Color.WHITE);
            paintParticula.setColor(Color.RED);
            paint.setColor(Color.BLACK);
            paint.setTextSize(22);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paintCurva = new Paint();
            paintCurva.setStyle(Paint.Style.STROKE);
            paintVector = new Paint();
            paintVector.setStrokeWidth(8);
            paintVector.setColor(Color.BLACK);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            x = w / 2;
            xmin = 0;
            y = 0;
            ymax = h - xmin;

            float anchura = w;
            float altura = h;
            trayectoria = new Trayectoria(anchura, altura);
            ymin = (int) trayectoria.getY(xmin);
            energia = 0.5f * velocidad * velocidad - aceleracion * ymin;
            control = "anchura:" + anchura + " altura:" + altura;
            path = new Path();
            path.moveTo(xmin, ymin);
            for (int i = xmin; i < anchura - xmin; i++) {
                float xi = i;
                float yi = trayectoria.getY(xi);
                path.lineTo(xi, yi);
            }
        }

        @Override
        public void run() {

            while (continuar) {
                cambiaPosicion();
                postInvalidate();
                try {
                    Thread.sleep(dt);
                } catch (InterruptedException e) {
                    ;
                }
            }
        }

        public void cambiaPosicion() {

            float[] tangente = {0, 0};
            tiempo = tiempo + dt;
            tangente = trayectoria.getTangente(x);
            float tangenteX = tangente[0];
            float tangenteY = tangente[1];
            tx = tangenteX * velocidad;
            ty = tangenteY * velocidad;
            float aT = aceleracion * tangenteY;
            velocidad = velocidad + aT * dt;
            float cinetica = velocidad * velocidad / 2;
            float yfloat = ((cinetica - energia) / aceleracion);
            y = (int) yfloat;
            if (y >= ymax) {
                x = (int) (x + Math.signum(velocidad) * dt);
                y = (int) trayectoria.getY(x);
                cambio = !cambio;
            }

            if (cambio) x = (int) trayectoria.getX2(y);
            else x = (int) trayectoria.getX1(y);
            if (y < ymin) velocidad = -velocidad;
        }

        @Override
        public void onDraw(Canvas canvas) {
            canvas.drawPaint(paintFondo);
            canvas.drawCircle(x, y, 30, paintParticula);
            canvas.drawText("X=" + x + " Y=" + y, 50, 50, paint);
            canvas.drawText("TIEMPO=" + tiempo, 50, 90, paint);
            canvas.drawText("VELOCIDAD=" + velocidad, 50, 130, paint);
            canvas.drawPath(path, paintCurva);
            canvas.drawText("COMPONENTES DE LA VELOCIDAD", 50, 170, paint);
            float e = 100; // escala para vector
            canvas.drawLine(x, y, x + e * tx, y + e * ty, paintVector);
            canvas.drawText("V", x + e * tx + 20, y + e * ty - 20, paint);
            canvas.drawText("VX=" + tx, 50, 200, paint);
            canvas.drawText("VY=" + ty, 50, 230, paint);
        }
    }

    class Trayectoria {

        float yMax, x0;

        Trayectoria(float anchura, float altura) {
            yMax = altura;
            x0 = anchura / 2;
        }

        float getY(float x) {
            float xx0 = (x - x0) / x0;
            float y = yMax * (1 - xx0 * xx0);
            return y;
        }

        float getX1(float y) {
            double raiz, radicando;
            radicando = Math.max(0f, 1 - y / yMax);
            raiz = Math.sqrt(radicando);
            float x1 = (float) (x0 + x0 * raiz);
            return x1;
        }

        float getX2(float y) {
            double raiz, radicando;
            radicando = Math.max(0f, 1 - y / yMax);
            raiz = Math.sqrt(radicando);
            float x2 = (float) (x0 - x0 * raiz);
            return x2;
        }

        float[] getTangente(float x) {
            float[] tangente = new float[2];
            float xx0 = (x - x0) / x0;
            float derivada = -yMax * 2 * xx0 / x0;
            double denominador = Math.sqrt(1 + derivada * derivada);
            tangente[0] = (float) (1 / denominador);
            tangente[1] = (float) (derivada / denominador);
            return tangente;

        }
    }

}

