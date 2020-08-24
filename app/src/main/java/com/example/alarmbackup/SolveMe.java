package com.example.alarmbackup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SolveMe extends AppCompatActivity {

    TextView math;
    EditText answer;
    int a;
    int b;
    int ans;
    ImageButton stop;
    Button enter;
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
        setContentView(R.layout.activity_solve_me);

        math=findViewById(R.id.math);
        answer=findViewById(R.id.input);
        calculate();

        stop=findViewById(R.id.STOP);
        enter=findViewById(R.id.enter);

        time=findViewById(R.id.TIME);
        timeis=findViewById(R.id.timeis);
        cancel=findViewById(R.id.cancel);
        String amorpm;
        background=findViewById(R.id.background);
        final Intent intent = new Intent(this, MyService.class);
        final Intent intent1=new Intent(this, MainActivity.class);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ans == Integer.parseInt(answer.getText().toString())) {
                        stopService(intent);
                        startActivity(intent1);
                    } else
                        Toast.makeText(SolveMe.this, "Enter the right answer!!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(SolveMe.this, "Enter a number!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        am_pm=getIntent().getIntExtra("am_pm",0);
        alarmHour = getIntent().getIntExtra("alarmHour", 0);
        alarmMinute = getIntent().getIntExtra("alarmMinute", 0);
        if(am_pm==1){
            time.setTextColor(Color.BLACK);
            timeis.setTextColor(Color.BLACK);
            cancel.setTextColor(Color.BLACK);
            amorpm="AM";

        }
        else{
            time.setTextColor(Color.WHITE);
            timeis.setTextColor(Color.WHITE);
            cancel.setTextColor(Color.WHITE);
            amorpm="PM";
        }
        time.setText(String.valueOf(String.valueOf(alarmHour)+":"+String.valueOf(alarmMinute)+" "+amorpm));


    }
    void calculate(){
        a=new Random().nextInt(100)+1;
        b=new Random().nextInt(100)+1;
        int choice=new Random().nextInt(4)+1;
        if(choice==1){//addition
            math.setText(String.valueOf(a)+" + "+String.valueOf(b)+ " = ");
            ans=a+b;
        }
        else if(choice==2){//multiplication
            math.setText(String.valueOf(a)+" * "+String.valueOf(b)+ " = ");
            ans=a*b;
        }
        else if(choice==3){//subtraction
            math.setText(String.valueOf(a)+" - "+String.valueOf(b)+ " = ");
            ans=a-b;
        }
        else if(a%b==0){//division
            math.setText(String.valueOf(a)+" / "+String.valueOf(b)+ " = ");
            ans=a/b;
        }
    }
}

