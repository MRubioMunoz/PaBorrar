package multimedia.rubio.miguel.anima02;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imagen);

        ValueAnimator movimiento = ValueAnimator.ofFloat(0,100f);
        movimiento.setInterpolator(new AccelerateDecelerateInterpolator());
        movimiento.setDuration(10000);
        movimiento.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                imageView.setRotation(progress);
                imageView.setRotationX(progress);
                imageView.setTranslationX(progress);
            }
        });
        movimiento.start();
    }
}
