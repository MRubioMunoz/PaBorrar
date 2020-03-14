package multimedia.rubio.miguel.anima01;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imagen);
        ObjectAnimator movimiento = ObjectAnimator.ofFloat(imageView,"translationX",200f);
        movimiento.setDuration(2000);
        movimiento.start();
    }
}
