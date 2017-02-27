package com.example.abed.skipe.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.abed.skipe.R;
import com.example.abed.skipe.utils.Session;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Session.getInstance().getUser();
        if (Session.getInstance().isUserLoggedIn() == true) {
            Toast.makeText(SplashActivity.this, Session.getInstance().getUser().email, Toast.LENGTH_SHORT).show();
            Intent gotohome = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(gotohome);
            finish();
        } else {
//
            Intent gotohome = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(gotohome);
            finish();
        }

    }
}
