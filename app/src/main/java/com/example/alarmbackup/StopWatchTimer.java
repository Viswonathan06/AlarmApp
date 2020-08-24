package com.example.alarmbackup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class StopWatchTimer extends AppCompatActivity {
    private Chronometer chronometer;
    private ImageButton Start;
    private ImageButton Stop;
    private ImageButton Pause;
    private boolean running;
    private long pauseDiff;
    private ImageButton Split;
    private int flag=1;
    private Chronometer time1;
    private Chronometer time2;
    private Chronometer time3;
    private Chronometer time4;
    private Chronometer time5;
    private Chronometer time6;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch_timer);
        chronometer=findViewById(R.id.chrono);
        Start=findViewById(R.id.START);
        Pause=findViewById(R.id.Pause);
        Stop=findViewById(R.id.stop);
        Split=findViewById(R.id.split);
        time1=findViewById(R.id.time1);
        time2=findViewById(R.id.time2);
        time3=findViewById(R.id.time3);
        time4=findViewById(R.id.time4);
        time5=findViewById(R.id.time5);
        time6=findViewById(R.id.time6);
        Split.setEnabled(false);




        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChronometer(v);
                Split.setEnabled(true);
            }
        });
        Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseChronometer(v);
                Split.setEnabled(false);


            }
        });
        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetChronometer(v);
                Split.setEnabled(false);

            }
        });
        Split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseDiff=SystemClock.elapsedRealtime()-chronometer.getBase();

                splitChronometer(v);
                flag++;

            }
        });
       // final Intent intent = new Intent(this, StopWatchServ.class);
        //ServiceCaller(intent);
    }






    public void startChronometer(View v){
        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime()-pauseDiff);
            chronometer.start();
            running=true;

        }
    }
    public void pauseChronometer(View v){
         if(running){

            chronometer.stop();
            running=false;
            pauseDiff=SystemClock.elapsedRealtime()-chronometer.getBase();
         }
    }
    public void resetChronometer(View v){
        chronometer.setBase(SystemClock.elapsedRealtime());
        time1.setBase(SystemClock.elapsedRealtime());
        time2.setBase(SystemClock.elapsedRealtime());
        time3.setBase(SystemClock.elapsedRealtime());
        time4.setBase(SystemClock.elapsedRealtime());
        time5.setBase(SystemClock.elapsedRealtime());
        time6.setBase(SystemClock.elapsedRealtime());

        pauseDiff=0;
    }

    public void splitChronometer(View v){
        if(flag==1){
            time1.setBase(SystemClock.elapsedRealtime()-pauseDiff);
        }
        else if(flag==2){
            time2.setBase(SystemClock.elapsedRealtime()-pauseDiff);

        }
        else if(flag==3){
            time3.setBase(SystemClock.elapsedRealtime()-pauseDiff);

        }
        else if(flag==4){
            time4.setBase(SystemClock.elapsedRealtime()-pauseDiff);

        }
        else if(flag==5){
            time5.setBase(SystemClock.elapsedRealtime()-pauseDiff);

        }
        else if(flag==6){
            time6.setBase(SystemClock.elapsedRealtime()-pauseDiff);

        }
        else{
            Toast.makeText(this, "Only 6 split times possible! Reset to start again!", Toast.LENGTH_SHORT).show();

        }
    }

   /* private void ServiceCaller(Intent intent) {
        stopService(intent);
        startService(intent);    }

    */
}
