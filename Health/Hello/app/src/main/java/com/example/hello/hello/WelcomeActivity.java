package com.example.hello.hello;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.hello.hello.LoginSignup.LoginActivity;
import com.example.hello.hello.LoginSignup.SignupActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {

    private VideoView bg_videoview;
    TextView welcome_text,started_text;
    Button login,sign_up;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bg_videoview = findViewById(R.id.video_id);
        mAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.login_button);
        sign_up = findViewById(R.id.sign_up_button);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,SignupActivity.class));
            }
        });

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.bg_video);
        bg_videoview.setVideoURI(uri);
        bg_videoview.start();

        bg_videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.setVolume(0f,0f);
            }
        });
        started_text = (TextView) findViewById(R.id.get_started_id);
        welcome_text = (TextView) findViewById(R.id.welcome_text_id);
        welcome_text.setText(R.string.login_quote);
        Handler handler = new Handler();
        final View v =  welcome_text;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation animate = new TranslateAnimation(0,-v.getWidth()-40,0,0);
                animate.setDuration(500);
                animate.setFillAfter(true);
                v.startAnimation(animate);
                v.setVisibility(View.INVISIBLE);
            }},2000);

        final View V = started_text;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation animate = new TranslateAnimation(v.getWidth(),0,0,0);
                animate.setDuration(500);
                animate.setFillAfter(true);
                V.startAnimation(animate);
                started_text.setText(R.string.get_started);

            }},2000);

        login = (Button) findViewById(R.id.login_button);
        sign_up = (Button) findViewById(R.id.sign_up_button);

        final View V_l = login;
        final View V_S = sign_up;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation animate = new TranslateAnimation(0,0,V_l.getHeight(),0);
                TranslateAnimation animate1 = new TranslateAnimation(0,0,V_S.getHeight(),0);
                animate.setDuration(500);
                animate1.setDuration(500);
                animate.setFillAfter(true);
                animate1.setFillAfter(true);
                V_l.startAnimation(animate);
                V_S.startAnimation(animate1);
                V_S.setVisibility(View.VISIBLE);
                V_l.setVisibility(View.VISIBLE);
            }},3000);


    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            startActivity(new Intent(WelcomeActivity.this,dashboardActivity.class));
        }

    }



}
