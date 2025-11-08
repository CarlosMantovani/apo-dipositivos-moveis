    package com.appweather.ui;

    import android.content.Intent;
    import android.os.Bundle;

    import androidx.appcompat.app.AppCompatActivity;

    import com.appweather.MainActivity;
    import com.appweather.databinding.ActivityIntroBinding;

    public class IntroActivity extends AppCompatActivity {

        private ActivityIntroBinding binding;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityIntroBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            binding.btnEnter.setOnClickListener(v -> {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            });
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            binding = null;
        }
    }
