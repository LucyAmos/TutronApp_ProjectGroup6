package com.example.tutrong6.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;


import com.example.tutrong6.R;

public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_DURATION = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an Intent to navigate to the next activity
                Intent intent = new Intent(SplashActivity.this, SignUpLauncherActivity.class);
                startActivity(intent);

                // Finish the splash activity
                finish();
            }
        }, SPLASH_DURATION);
    }
}