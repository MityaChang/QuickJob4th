package com.example.quickjob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quickjob.Model.DataCenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

public class AddJob extends AppCompatActivity {

     EditText jobTitle, jobDesc, jobSalary, jobSkills;
     Button postJobBtn;
     TextView displayTexts;

     FirebaseAuth mAuth;
     DatabaseReference PostJob;

     DatabaseReference mDatabase;

//     DatabaseReference RootPostJob;
//     String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        displayTexts = findViewById(R.id.displayText);

        mAuth = FirebaseAuth.getInstance();
        //Create unique user and have unique user job post
        FirebaseUser mUser = mAuth.getCurrentUser();
        String userID = mUser.getUid();

        //Inside user ID will have their job post
        PostJob = FirebaseDatabase.getInstance().getReference().child("Job_Post").child(userID);

//        RootPostJob = FirebaseDatabase.getInstance().getReference();
        Log.e("ID", userID);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Database");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AddJob();
//        PostJob.child(userID).setValue("data");
    }

     void AddJob() {
        jobTitle = findViewById(R.id.jobTitle);
        jobDesc = findViewById(R.id.jobDesc);
        jobSkills = findViewById(R.id.jobSkills);
        jobSalary = findViewById(R.id.jobSalary);
        postJobBtn = findViewById(R.id.jobPostBtn);

        postJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = jobTitle.getText().toString();
                String description = jobDesc.getText().toString();
                String skills = jobSkills.getText().toString();
                String salarys = jobSalary.getText().toString();

                if (TextUtils.isEmpty(title)) {
                    jobTitle.setError("Please fill up the required field!");
                    return;
                }
                if (TextUtils.isEmpty(description)) {
                    jobDesc.setError("Please fill up the required field!");
                    return;
                }
                if (TextUtils.isEmpty(skills)) {
                    jobSkills.setError("Please fill up the required field!");
                    return;
                }
                if (TextUtils.isEmpty(salarys)) {
                    jobSalary.setError("Please fill up the required field!");
                    return;
                }

//                RootPostJob = RootPostJob.child("Job_Post").child(userID);
//                HashMap<String,String>RootPostHash = new HashMap<>();
//                RootPostHash.put("title",title);
//                RootPostHash.put("description",description);
//                RootPostHash.put("skills",skills);
//                RootPostHash.put("salary",salarys);
//                RootPostJob.setValue(RootPostHash);


                //get Random key from the database
                String id = PostJob.push().getKey();
                Log.e("IDS", id);
                String date = DateFormat.getDateInstance().format(new Date());

                DataCenter data = new DataCenter(title, description, skills, salarys, id, date);
                Log.e("title", title);

                PostJob.child(id).setValue(data);

                mDatabase.child(id).setValue(data);

                Toast.makeText(getApplicationContext(), "Successfully inserted", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), com.example.quickjob.PostJob.class));


            }
        });


    }
}