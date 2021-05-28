package com.example.quickjob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class JobDetails extends AppCompatActivity {

     TextView mTitle,mDate,mDesc,mSkills,mSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        mTitle = findViewById(R.id.JobDetailsTitle);
        mDate = findViewById(R.id.JobDetailsDate);
        mDesc = findViewById(R.id.JobDetailsDesc);
        mSkills = findViewById(R.id.JobDetailsSkills);
        mSalary = findViewById(R.id.JobDetailsSalary);

        //Receive data from all job activity using intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String date = intent.getStringExtra("date");
        String description = intent.getStringExtra("description");
        String skill = intent.getStringExtra("skills");
        String salary = intent.getStringExtra("salary");

        //Set into textField
        mTitle.setText(title);
        mDate.setText(date);
        mDesc.setText(description);
        mSkills.setText(skill);
        mSalary.setText(salary);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}