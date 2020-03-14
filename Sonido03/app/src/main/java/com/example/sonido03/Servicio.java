package com.example.sonido03;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;


public class Servicio extends Service {

    private MediaPlayer mediaPlayer;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        mediaPlayer = MediaPlayer.create(this,R.raw.ezio);
        mediaPlayer.setLooping(true);
    }

    @Override
    public void onDestroy(){
        mediaPlayer.stop();
        Toast.makeText(getApplicationContext(),"Parado",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart(Intent intent, int startid){
        mediaPlayer.start();
        Toast.makeText(getApplicationContext(),"Iniciado",Toast.LENGTH_SHORT).show();
    }
}
