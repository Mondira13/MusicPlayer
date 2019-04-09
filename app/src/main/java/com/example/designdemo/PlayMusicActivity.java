package com.example.designdemo;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.SeekBar;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayMusicActivity extends AppCompatActivity {

    TextView songName;
    CircleImageView circleSmallImage;
    CircleImageView songIcon;
    boolean isPlay = false;

    RotateAnimation animation;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        initializeView();
        mediaPlayer = MediaPlayer.create(this, R.raw.duniyaa);

        circleSmallImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPlay) {
                    circleSmallImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_circle_filled_black_24dp));
                    isPlay = true;
                    mediaPlayer.start();
                    animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(1000);
                    animation.setRepeatCount(Animation.INFINITE);
                    animation.setInterpolator(new LinearInterpolator());
                    songIcon.setAnimation(animation);
                    songIcon.startAnimation(animation);
                } else {
                    circleSmallImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_circle_filled_black_24dp));
                    isPlay = false;
                    mediaPlayer.pause();
                    animation.cancel();
                }
            }
        });


    }

    private void initializeView(){
        circleSmallImage = findViewById(R.id.circleSmallImage);
        songName = findViewById(R.id.songName);
        songIcon = findViewById(R.id.songIcon);
        seekBar = findViewById(R.id.seekbar);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }
}
