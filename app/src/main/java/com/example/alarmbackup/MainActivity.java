package com.example.alarmbackup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button stopwatch;
    Button timer;
    int flag=0;


    CheckBox tune1;
    CheckBox tune2;
    CheckBox tune3;

    String alarmMin;
    String am_pm;
    int tune;
    int MakeItHard;
    ImageButton setAlarm;
    EditText message;
    private TimePicker timePicker;
    private TextView display;
    Integer alarmHour ;
    Integer alarmMinute;
    boolean[] DaysWeek = new boolean[7];
    Switch hard;
    CheckBox sun;
    CheckBox mon;
    CheckBox tue;
    CheckBox wed;
    CheckBox thu;
    CheckBox fri;
    CheckBox sat;


    ArrayList<Integer> WeekDays= new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hard=findViewById(R.id.hard);

        resetWeeks();
        timePicker = findViewById(R.id.timPicker);

        stopwatch=findViewById(R.id.stopwatch);
        timer=findViewById(R.id.Timer);
        message=findViewById(R.id.message);
        setAlarm=findViewById(R.id.setAlarm);


        sun=findViewById(R.id.checkSun);
        mon=findViewById(R.id.checkMon);
        tue=findViewById(R.id.checkTue);
        wed=findViewById(R.id.checkWed);
        thu=findViewById(R.id.checkThu);
        fri=findViewById(R.id.checkFri);
        sat=findViewById(R.id.checkSat);

        tune1=findViewById(R.id.tune1);
        tune2=findViewById(R.id.tune2);
        tune3=findViewById(R.id.tune3);
        tune=0;

        setEnabled();
        setAlarm.setEnabled(true);

        final Intent intent = new Intent(this, MyService.class);
        //ServiceCaller(intent);
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MainActivity.this,Timer.class);
                startActivity(intent2);
            }
        });
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                setEnabled();
                //ServiceCaller(intent);
            }
        });

        stopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent=new Intent(MainActivity.this,StopWatchTimer.class);
                        startActivity(intent);
            }
        });
        final Ringtone ringtone1 = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri((RingtoneManager.TYPE_ALARM)));
        final Ringtone ringtone2 = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri((RingtoneManager.TYPE_NOTIFICATION)));
        final Ringtone ringtone3 = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri((RingtoneManager.TYPE_RINGTONE)));

        hard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    MakeItHard=1;
                }
                else{
                    MakeItHard=0;
                }
            }
        });



        tune1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ringtone2.stop();
                ringtone3.stop();
                tune2.setChecked(false);
                tune3.setChecked(false);
                tune=1;
                if(isChecked) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        ringtone1.setLooping(false);
                        ringtone1.play();
                    }
                    else{
                        ringtone1.play();
                        ringtone1.stop();

                    }
                }
                else if(!isChecked)
                    ringtone1.stop();


            }
        });
        tune2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ringtone1.stop();
                ringtone3.stop();
                tune1.setChecked(false);
                tune3.setChecked(false);
                tune=2;
                if(isChecked) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        ringtone2.setLooping(false);
                        ringtone2.play();
                    }
                    else{
                        ringtone2.play();
                        ringtone2.stop();

                    }
                }
                else if(!isChecked)
                    ringtone2.stop();


            }
        });
        tune3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ringtone2.stop();
                ringtone1.stop();
                tune1.setChecked(false);
                tune2.setChecked(false);
                tune=3;
                if(isChecked) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        ringtone3.setLooping(false);
                        ringtone3.play();
                    }
                    else{
                            ringtone3.play();
                            ringtone3.stop();

                    }
                }
                else if(!isChecked)
                    ringtone3.stop();

                }


            });

        //CHECK DAYS TO RING

        sun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                     WeekDays.add(0,1);
                     setAlarm.setEnabled(true);
                     //ServiceCaller(intent);

                }
                else WeekDays.add(0,0);

            }
        });
        mon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    WeekDays.add(1,1);
                    setAlarm.setEnabled(true);

                    //erviceCaller(intent);

                }
                else WeekDays.add(1,0);
            }
        });
        tue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    WeekDays.add(2,1);
                    setAlarm.setEnabled(true);

//                    ServiceCaller(intent);

                }
                else WeekDays.add(2,0);
            }


        });
        wed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    WeekDays.add(3,1);
                    setAlarm.setEnabled(true);

//                    ServiceCaller(intent);

                }
                else WeekDays.add(3,0);

            }
        });
        thu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    WeekDays.add(4,1);
                    setAlarm.setEnabled(true);

//                    ServiceCaller(intent);

                }
                else WeekDays.add(4,0);
            }
        });
        fri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    WeekDays.add(5,1);
                    setAlarm.setEnabled(true);

//                    ServiceCaller(intent);

                }
                else WeekDays.add(5,0);
            }
        });
        sat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    WeekDays.add(6,1);
                    setAlarm.setEnabled(true);

//                    ServiceCaller(intent);

                }
                else WeekDays.add(6,0);
            }
        });
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ringtone1.stop();
                ringtone2.stop();
                ringtone3.stop();
                if(sun.isChecked()||mon.isChecked()||tue.isChecked()||wed.isChecked()||thu.isChecked()||fri.isChecked()||sat.isChecked()) {
                    alarmMin = String.valueOf(alarmMinute);
                    am_pm = "AM";
                    if (Build.VERSION.SDK_INT >= 23) {
                        alarmHour = timePicker.getHour();
                        alarmMinute = timePicker.getMinute();


                    } else {
                        alarmHour = timePicker.getCurrentHour();
                        alarmMinute = timePicker.getCurrentMinute();
                    }
                    if (alarmMinute < 10) {
                        alarmMin = "0" + String.valueOf(alarmMinute);
                    }
                    if (alarmHour > 12) {
                        am_pm = "PM";
                        alarmHour = alarmHour - 12;
                    }
                    else if(alarmHour==12){
                        alarmHour=12;
                        am_pm="PM";
                    }
                    else if(alarmHour==0){
                        alarmHour=12;
                        am_pm="AM";
                    }



                    Toast.makeText(MainActivity.this, "Your alarm is set at " + String.valueOf(alarmHour) + ":" +String.valueOf(alarmMin)+ " " + am_pm, Toast.LENGTH_SHORT).show();
                    intent.putExtra("tune",tune);
                    intent.putExtra("Hard",MakeItHard);
                    ServiceCaller(intent);
                }
                else
                    Toast.makeText(MainActivity.this, "Enter days to ring!", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void setEnabled(){
        sun.setEnabled(true);
        mon.setEnabled(true);
        tue.setEnabled(true);
        wed.setEnabled(true);
        thu.setEnabled(true);
        fri.setEnabled(true);
        sat.setEnabled(true);
        //Toast.makeText(this, "Buttons enabled", Toast.LENGTH_SHORT).show();
    }

    private void resetWeeks(){
        for(int i=0;i<7;i++) {
            WeekDays.add(i,0);
        }
    }

    private void ServiceCaller(Intent intent){

        stopService(intent);
        //resetWeeks();
        if (Build.VERSION.SDK_INT >= 23 ){
             alarmHour = timePicker.getHour();
             alarmMinute = timePicker.getMinute();

        }
        else{
             alarmHour = timePicker.getCurrentHour();
             alarmMinute = timePicker.getCurrentMinute();
        }
//        display.setText(String.valueOf(alarmHour)+String.valueOf(alarmMinute));

        String AlarmMessage=message.getText().toString();
        intent.putExtra("Message", AlarmMessage);

        intent.putExtra("alarmHour", alarmHour);
        Log.e("Hour is",String.valueOf(alarmHour));


        intent.putExtra("alarmMinute", alarmMinute);
        intent.putIntegerArrayListExtra("DaysToRing",WeekDays);
        startService(intent);
    }
}