package com.example.alarmbackup;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Timer extends AppCompatActivity {
    private static long START_TIME_IN_MILLIS=0;
    private TextView mTextViewCountDown;
    private ImageButton mButtonStartPause;
    private EditText seco;
    private Button enter;
    private EditText minu;
    private Ringtone ringtone;
    private ImageButton mButtonReset;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
            enter=(Button)findViewById(R.id.enter);
            mTextViewCountDown = findViewById(R.id.text_view_countdown);
            mButtonStartPause = findViewById(R.id.button_start_pause);
            mButtonReset = findViewById(R.id.button_reset);
            seco=(EditText)findViewById(R.id.seconds);
            minu=(EditText)findViewById(R.id.minutes);
            mButtonStartPause.setEnabled(false);
            enter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(minu.getText().toString().isEmpty()||seco.getText().toString().isEmpty()){
                        Toast.makeText(Timer.this, "Enter both minutes and seconds!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        try {
                            int min = Integer.parseInt(minu.getText().toString());
                            int sec = Integer.parseInt(seco.getText().toString());
                            START_TIME_IN_MILLIS = min * 1000 * 60 + sec * 1000;
                            resetTimer();
                            mButtonStartPause.setEnabled(true);
                            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", min, sec);
                            //mTextViewCountDown.setText(timeLeftFormatted);
                        }
                        catch (NumberFormatException e){
                            Toast.makeText(Timer.this, "Enter a number for both!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });

            mButtonStartPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTimerRunning) {
                        pauseTimer();
                    } else {
                        startTimer();
                    }
                }
            });
            mButtonReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetTimer(); pauseTimer();
                }
            });
            updateCountDownText();

        }
        private void startTimer() {
            mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mTimeLeftInMillis = millisUntilFinished;
                    updateCountDownText();
                }
                @Override
                public void onFinish() {
                    mTimerRunning = false;
                    mButtonStartPause.setImageResource(R.drawable.icons8_play_64);
                    mButtonStartPause.setVisibility(View.INVISIBLE);
                    ringtone = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri((RingtoneManager.TYPE_NOTIFICATION)));
                    ringtone.play();
                    //mButtonReset.setVisibility(View.VISIBLE);
                }
            }.start();
            mTimerRunning = true;
            mButtonStartPause.setImageResource(R.drawable.icons8_pause_64);
            //mButtonReset.setVisibility(View.VISIBLE);
        }
        private void pauseTimer() {
            mCountDownTimer.cancel();
            mTimerRunning = false;
            mButtonStartPause.setImageResource(R.drawable.icons8_play_64);
            //mButtonReset.setVisibility(View.VISIBLE);
        }
        private void resetTimer() {
            mTimeLeftInMillis = START_TIME_IN_MILLIS;
            updateCountDownText();
            mButtonStartPause.setImageResource(R.drawable.icons8_play_64);

            //mButtonReset.setVisibility(View.INVISIBLE);
            mButtonStartPause.setVisibility(View.VISIBLE);
        }
        private void updateCountDownText() {
            int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
            int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
            mTextViewCountDown.setText(timeLeftFormatted);
        }
    }

