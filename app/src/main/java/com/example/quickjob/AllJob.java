package com.example.quickjob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quickjob.Model.DataCenter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AllJob extends AppCompatActivity {

     RecyclerView recyclerView;

     DatabaseReference mAllPostedJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_job);

        recyclerView = findViewById(R.id.recylerAllJob);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAllPostedJobs = FirebaseDatabase.getInstance().getReference().child("Database");
        mAllPostedJobs.keepSynced(true);
    }

    public static class AllPostedJobViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public AllPostedJobViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setJobTitle(String title) {
            TextView mTitle = mView.findViewById(R.id.jobPostedTitle);
            mTitle.setText(title);
//            Log.e("serJobTitle123",title);
        }

        public void setJobDate(String date) {
            TextView mDate = mView.findViewById(R.id.jobPostedDate);
            mDate.setText(date);
        }

        public void setJobDesc(String desc) {
            TextView mDesc = mView.findViewById(R.id.jobPostedDescription);
            mDesc.setText(desc);
        }

        public void setJobSkills(String skills) {
            TextView mSkills = mView.findViewById(R.id.jobPostedSkills);
            mSkills.setText(skills);
        }

        public void setJobSalary(String salary) {
            TextView mSalary = mView.findViewById(R.id.jobPostedSalary);
            mSalary.setText(salary);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<DataCenter> options =
                new FirebaseRecyclerOptions.Builder<DataCenter>()
                        .setQuery(mAllPostedJobs, DataCenter.class)
                        .setLifecycleOwner(this)
                        .build();


        FirebaseRecyclerAdapter<DataCenter, AllJob.AllPostedJobViewHolder> adapter = new FirebaseRecyclerAdapter<DataCenter, AllJob.AllPostedJobViewHolder>(
                options
        ) {
            @NonNull
            @Override
            public AllPostedJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allpostjob, parent, false);
                return new AllJob.AllPostedJobViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull AllPostedJobViewHolder holder, int position, @NonNull DataCenter model) {

                holder.setJobTitle(model.getTitle());
                holder.setJobDate(model.getDate());
                holder.setJobDesc(model.getDescription());
                holder.setJobSkills(model.getSkills());
                holder.setJobSalary(model.getSalary());
//                if(model.getSalary() != null){
//                    Log.e("jobby","null");
//                }
//                Log.e("jobtitle1234",model.getDescription());
            }
        };
        recyclerView.setAdapter(adapter);
    }
}