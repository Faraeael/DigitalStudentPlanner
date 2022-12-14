package com.example.digitalstudentplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button planner;
    Button createSched;
    Button Sched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        planner =findViewById(R.id.PlannerBtn);
        createSched = findViewById(R.id.createSchedBtn);
        Sched = findViewById(R.id.scheduleBtn);

        planner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CalendarPlanner.class));
            }
        });
    }
}