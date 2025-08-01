package com.example.oscilatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView oscilatorImage;
    private Button toggleButton;
    private ObjectAnimator animator;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oscilatorImage = findViewById(R.id.oscilator_image);
        toggleButton = findViewById(R.id.toggle_button);

        // Simple up and down animation
        animator = ObjectAnimator.ofFloat(oscilatorImage, "translationY", 0f, 200f, 0f);
        animator.setDuration(2000); // 2 seconds
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ObjectAnimator.INFINITE);

        // Prepare the sound player (you'll need an audio file named 'oscilator_sound.mp3' in res/raw)
        mediaPlayer = MediaPlayer.create(this, R.raw.oscilator_sound);
        mediaPlayer.setLooping(true);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    stopOscilator();
                } else {
                    startOscilator();
                }
            }
        });
    }

    private void startOscilator() {
        animator.start();
        mediaPlayer.start();
        toggleButton.setText("Stop Oscilator");
        isPlaying = true;
    }

    private void stopOscilator() {
        animator.cancel();
        mediaPlayer.pause();
        toggleButton.setText("Start Oscilator");
        isPlaying = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
