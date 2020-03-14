package multimedia.rubio.miguel.anima05;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Drawable drawable;
    private AnimatedVectorDrawable avd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad1);

        imageView = findViewById(R.id.imagen);
        drawable = imageView.getDrawable();

        avd = (AnimatedVectorDrawable) drawable;
        avd.start();
        //android:valueTo="M 30,75 Q 50,55 70,75"
    }
}
