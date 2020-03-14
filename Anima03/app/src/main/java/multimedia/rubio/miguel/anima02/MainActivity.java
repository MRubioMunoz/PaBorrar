package multimedia.rubio.miguel.anima02;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ObjectAnimator mueve1;
    ObjectAnimator mueve2;
    ObjectAnimator mueve3;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen = findViewById(R.id.imagen);
        mueve1 = ObjectAnimator.ofFloat(imagen,"translationX",400f);
        mueve1.setDuration(2000);
        mueve2 = ObjectAnimator.ofFloat(imagen,"translationY", 400f);
        mueve2.setDuration(2000);
        mueve3 = ObjectAnimator.ofFloat(imagen,"rotation",400f);
        mueve3.setDuration(2000);

        AnimatorSet variosMovimientos = new AnimatorSet();
        variosMovimientos.playTogether(mueve1,mueve2,mueve3);
        variosMovimientos.start();
    }
}
