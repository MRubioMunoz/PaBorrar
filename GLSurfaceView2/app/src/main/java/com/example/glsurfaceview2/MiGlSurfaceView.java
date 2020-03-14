package com.example.glsurfaceview2;

import android.content.Context;
import android.graphics.Color;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MiGlSurfaceView extends GLSurfaceView {
    Renderizado miRender;


    public MiGlSurfaceView(Context context) {
        super(context);
        miRender = new Renderizado();
        setRenderer(miRender);
    }


    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        queueEvent(new Runnable() {
            @Override
            public void run() {
                miRender.setColor(event.getX()/getWidth(),
                        event.getY()/getHeight(),
                        1.0f);
            }
        });
        return true;
    }

}

class Renderizado implements GLSurfaceView.Renderer {
    private float color1 = Color.RED;
    private float color2 = Color.GREEN;
    private float color3 = Color.BLUE;

    public void setColor(float a, float b, float c) {
        color1 = a;
        color2 = b;
        color3 = c;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(color1,color2,color3,1.0f);
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_NICEST);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glDisable(GL10.GL_DITHER);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0)
            height = 1;
        float aspecto = (float) width/height;
        gl.glViewport(0,0,width,height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl,45,aspecto,0.1f,100.0f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(color1,color2,color3,1.0f);
    }
}
