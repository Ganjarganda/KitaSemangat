package gans.apps.kitasemangat.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import gans.apps.kitasemangat.BuildConfig;
import gans.apps.kitasemangat.R;

public class MainActivity extends AppCompatActivity {

    TextView tv_version;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_version = findViewById(R.id.tv_version);
        tv_version.setText("Version " + BuildConfig.VERSION_NAME);

        Timer RunSplash = new Timer();
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                finish();
                //Pindah ke Activity Dashboard
                startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        };
        RunSplash.schedule(ShowSplash,3000);

    }
}