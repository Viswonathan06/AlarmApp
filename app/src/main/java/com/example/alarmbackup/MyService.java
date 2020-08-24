package com.example.alarmbackup;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.alarmbackup.App.CHANNEL_ID;

public class MyService extends Service {

    private Integer alarmHour;
    private Integer alarmMinute;
    private Ringtone ringtone;
    int am_pm;
    PendingIntent pendingIntent;
    int flag1=0;
    ArrayList<Integer> DaysToRing=new ArrayList<Integer>();
    private Timer t = new Timer();
    int dayOfWeek;
    int tune;
    int MakeItHard;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    Switch hard;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        alarmHour = intent.getIntExtra("alarmHour", 0);
        alarmMinute = intent.getIntExtra("alarmMinute", 0);
        tune = intent.getIntExtra("tune", 0);
        MakeItHard=intent.getIntExtra("Hard",0);
        am_pm = 1;


        if (alarmHour > 12) {
            alarmHour = alarmHour - 12;
            am_pm = 0;
        }
        DaysToRing = intent.getIntegerArrayListExtra("DaysToRing");


        if (tune == 1) {
             ringtone = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri((RingtoneManager.TYPE_ALARM)));
        }
        else if(tune==2) {
            ringtone = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri((RingtoneManager.TYPE_NOTIFICATION)));
        }
        else {
            ringtone = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri((RingtoneManager.TYPE_RINGTONE)));
        }







        //Toast.makeText(MyService.this, String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)), Toast.LENGTH_SHORT).show();
        dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        final Intent intentNew=new Intent(this,AlarmBackDrop.class);
        final Intent IntentHard=new Intent(this,SolveMe.class);
        intentNew.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intentNew.putExtra("alarmHour", alarmHour);
        intentNew.putExtra("alarmMinute", alarmMinute);
        intentNew.putExtra("am_pm", am_pm);

        IntentHard.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        IntentHard.putExtra("alarmHour", alarmHour);
        IntentHard.putExtra("alarmMinute", alarmMinute);
        IntentHard.putExtra("am_pm", am_pm);

        int flag=0;
        for(int i=0;i<7;i++){
            if(DaysToRing.get(i)==0){
                flag++;
            }
        }
        //Toast.makeText(MyService.this, String.valueOf(flag), Toast.LENGTH_SHORT).show();
        flag1=0;


        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

               // Toast.makeText(MyService.this, "Today is"+String.valueOf(dayOfWeek), Toast.LENGTH_SHORT).show();

                if (DaysToRing.get(dayOfWeek-1) == 1) {

                    if (Calendar.getInstance().get(Calendar.HOUR) == alarmHour &&
                            Calendar.getInstance().get(Calendar.MINUTE) == alarmMinute) {
                        if(flag1==0) {
                            if(MakeItHard==1){
                                startActivity(IntentHard);
                            }
                            else if(MakeItHard==0){
                                startActivity(intentNew);
                            }
                        }

                        ringtone.play();
                        flag1++;

//                   Toast.makeText(MyService.this, "RINGTONE IS PLAYING", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        ringtone.stop();
                        flag1=0;
                    }
                }
                else {
                    ringtone.stop();
                    flag1=0;
                }


            }
        }, 0, 2000);
        String Message=intent.getStringExtra("Message");


        if(MakeItHard==1){
            pendingIntent = PendingIntent.getActivity(this, 0, IntentHard, 0);
        }
        else if(MakeItHard==0) {
            pendingIntent = PendingIntent.getActivity(this, 0, intentNew, 0);
        }

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example Service")
                .setContentText(Message)
                .setSmallIcon(R.drawable.icons8_alarm_clock_64)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        //do heavy work on a background thread
        //stopSelf();
        return START_NOT_STICKY;
       // return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        ringtone.stop();
        t.cancel();
        flag1=0;
        super.onDestroy();
    }
}