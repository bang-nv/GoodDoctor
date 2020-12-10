package vn.haui.gooddoctor.ui.splash;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import vn.haui.gooddoctor.data.prefs.SessionManager;
import vn.haui.gooddoctor.databinding.ActivitySplashBinding;
import vn.haui.gooddoctor.ui.auth.AuthActivity;
import vn.haui.gooddoctor.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    private void init() {
        sessionManager = new SessionManager(getBaseContext());

        new Handler().postDelayed(runnableDirect, 800);
    }

    private Runnable runnableDirect = new Runnable() {
        @Override
        public void run() {
            finish();
            if (!sessionManager.isLoggedIn()) {
                startActivity(AuthActivity.newInstance(SplashActivity.this));
            } else {
                startActivity(MainActivity.newInstance(SplashActivity.this));
                finish();
            }
        }
    };
}