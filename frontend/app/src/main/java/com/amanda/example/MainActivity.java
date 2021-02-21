package com.amanda.example;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //button objects.
    private Button buttonStart;
    private Button buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting buttons from xml.
        buttonStart = findViewById(R.id.buttonStart);
        buttonStop = findViewById(R.id.buttonStop);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonStart) {
            //starting service
            startService(new Intent(this, MusicPlayer.class));
        } else if (view == buttonStop) {
            //stopping service
            stopService(new Intent(this, MusicPlayer.class));
        }
    }
}
