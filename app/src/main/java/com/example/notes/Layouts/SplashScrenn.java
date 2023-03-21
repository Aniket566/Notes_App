package com.example.notes.Layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.notes.MainActivity;
import com.example.notes.R;

public class SplashScrenn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splash_screnn );
        getSupportActionBar ().hide ();

        new Handler ().postDelayed ( new Runnable () {
            @Override
            public void run() {
               Intent intent=new Intent (SplashScrenn.this, MainActivity.class);
               startActivity ( intent );
               finish ();
            }
        },2000 );
    }
}