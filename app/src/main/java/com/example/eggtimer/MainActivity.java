package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    int countdown_time;
    Button button;

    MediaPlayer mediaPlayer;
    public void play(View view){
        mediaPlayer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        countdown_time=30;

        seekBar=findViewById(R.id.seekBar);
        seekBar.setMax(300);
        seekBar.setProgress(30);

        button=findViewById(R.id.button);

        mediaPlayer=MediaPlayer.create(this,R.raw.oneplus_tune);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                countdown_time=i;
                if((i%60)<10)
                    textView.setText((i/60)+":0"+(i%60));
                else
                    textView.setText((i/60)+":"+(i%60));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekBar.setEnabled(false);
                
                new CountDownTimer(countdown_time*1000,1000){

                    @Override
                    public void onTick(long l) {
                        l=l/1000;
                        if((l%60)<10)
                            textView.setText((l/60)+":0"+(l%60));
                        else
                            textView.setText((l/60)+":"+(l%60));

                    }

                    @Override
                    public void onFinish() {
                        textView.setText("Finished");
                        mediaPlayer.start();
                        seekBar.setEnabled(true);

                    }
                }.start();
            }
        });

    }
}