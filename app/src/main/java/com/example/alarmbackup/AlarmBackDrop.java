package com.example.alarmbackup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AlarmBackDrop extends AppCompatActivity {
    ImageButton stop;
    TextView time;
    int alarmHour;
    int alarmMinute;
    TextView timeis;
    TextView Time;
    TextView cancel;
    LinearLayout background;
    int am_pm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_back_drop);
        stop=findViewById(R.id.STOP);
        time=findViewById(R.id.TIME);
        timeis=findViewById(R.id.timeis);
        cancel=findViewById(R.id.cancel);
        String amorpm;
        background=findViewById(R.id.background);
        final Intent intent = new Intent(this, MyService.class);
        final Intent intent1=new Intent(this, MainActivity.class);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
                startActivity(intent1);
            }
        });

        am_pm=getIntent().getIntExtra("am_pm",0);
        alarmHour = getIntent().getIntExtra("alarmHour", 0);
        alarmMinute = getIntent().getIntExtra("alarmMinute", 0);
        if(am_pm==1){
            background.setBackgroundResource(R.drawable.day);
            time.setTextColor(Color.BLACK);
            timeis.setTextColor(Color.BLACK);
            cancel.setTextColor(Color.BLACK);
            amorpm="AM";

        }
        else{
            background.setBackgroundResource(R.drawable.night);
            time.setTextColor(Color.WHITE);
            timeis.setTextColor(Color.WHITE);
            cancel.setTextColor(Color.WHITE);
            amorpm="PM";
        }
        time.setText(String.valueOf(String.valueOf(alarmHour)+":"+String.valueOf(alarmMinute)+" "+amorpm));

    }
}
