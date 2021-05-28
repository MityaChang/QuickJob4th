package com.example.quickjob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

     Button alljobBtn, postjobBtn;

//     Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        alljobBtn = findViewById(R.id.Alljobbtn);
        postjobBtn = findViewById(R.id.Postjobbtn);

//        toolbar = findViewById(R.id.toolbarHome);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setTitle("Job Portal");

        alljobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AllJob.class));

            }
        });

        postjobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), PostJob.class));

            }
        });
    }
}