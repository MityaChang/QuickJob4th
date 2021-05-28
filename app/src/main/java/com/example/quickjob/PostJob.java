package com.example.quickjob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quickjob.Model.DataCenter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PostJob extends AppCompatActivity {

    FloatingActionButton AddBtn;

    RecyclerView recyclerView;
    TextView displaytexts;

    FirebaseAuth mAuth;
    DatabaseReference DataPostJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);
        displaytexts = findViewById(R.id.displayText);


        AddBtn = findViewById(R.id.addBtn);
        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddJob.class));

            }
        });
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String UID = mUser.getUid();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DataPostJob = FirebaseDatabase.getInstance().getReference().child("Job_Post").child(UID);

        recyclerView = findViewById(R.id.recylerJobPostID);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<DataCenter> options =
                new FirebaseRecyclerOptions.Builder<DataCenter>()
                        .setQuery(DataPostJob, DataCenter.class)
                        .setLifecycleOwner(this)
                        .build();


        FirebaseRecyclerAdapter<DataCenter, AllJob.AllPostedJobViewHolder> adapter = new FirebaseRecyclerAdapter<DataCenter, AllJob.AllPostedJobViewHolder>(
                options
        ) {
            @NonNull
            @Override
            public AllJob.AllPostedJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_job_item, parent, false);
                return new AllJob.AllPostedJobViewHolder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull AllJob.AllPostedJobViewHolder holder, int position, @NonNull DataCenter model) {

                holder.setJobTitle(model.getTitle());
                holder.setJobDate(model.getDate());
                holder.setJobDesc(model.getDescription());
                holder.setJobSkills(model.getSkills());
                holder.setJobSalary(model.getSalary());
            }
        };

        recyclerView.setAdapter(adapter);
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        View JobView;


        public viewHolder(View itemView) {
            super(itemView);
            JobView = itemView;
        }

        public void setJobTitle(String title) {
            TextView mTitle = JobView.findViewById(R.id.jobTitle);
            mTitle.setText(title);
        }

        public void setJobDate(String date) {
            TextView mDate = JobView.findViewById(R.id.jobDate);
        }

        public void setJobDesc(String description) {
            TextView mDesc = JobView.findViewById(R.id.jobDesc);
            mDesc.setText(description);
        }

        public void setJobSkills(String skills) {
            TextView mSkills = JobView.findViewById(R.id.jobSkills);
            mSkills.setText(skills);
        }

        public void setJobSalary(String salary) {
            TextView mSalary = JobView.findViewById(R.id.jobSalaries);
            mSalary.setText(salary);
        }

    }
}