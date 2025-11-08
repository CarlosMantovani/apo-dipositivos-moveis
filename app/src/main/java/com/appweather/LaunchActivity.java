package com.appweather;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launch);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(LaunchActivity.this, MainActivity.class));
            finish();
        }, 3000);
    }
}
