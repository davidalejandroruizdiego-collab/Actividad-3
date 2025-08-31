package com.example.actividad3;

import android.os.Bundle;
import android.view.ScaleGestureDetector;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private float scalaFactor = 1.0f;
    ScaleGestureDetector scaleGestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        Toast.makeText(this,"Bienvenido a TecMilenio",Toast.LENGTH_SHORT).show();

        final TextView textView = (TextView) findViewById(R.id.textView);
        final Button button = (Button) findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        button.setOnClickListener(v-> textView.setText("Chimichangas"));

        imageView.setOnTouchListener((v, event)-> {
            scaleGestureDetector.onTouchEvent(event);
            textView.setText("Touch");
            return true;
        });
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scale = detector.getScaleFactor();
            scalaFactor *= scale;
            scalaFactor = Math.max(0.1f, Math.min(scalaFactor, 5.0f));

            imageView.setScaleX(scalaFactor);
            imageView.setScaleY(scalaFactor);
            return true;
        }
    }
}