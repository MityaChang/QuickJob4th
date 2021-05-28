package com.example.quickjob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

     EditText emailRegister , passwordRegister;
     Button RegisterBtn , LoginBtn;

     FirebaseAuth mAuth;

     ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);
        Register();
    }

     void Register(){
        emailRegister = findViewById(R.id.EmailRegister);
        passwordRegister = findViewById(R.id.PasswordRegister);
        RegisterBtn = findViewById(R.id.Registerbtn);
        LoginBtn = findViewById(R.id.loginbtn);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailRegi = emailRegister.getText().toString().trim();
                String passRegi = passwordRegister.getText().toString().trim();
                if(TextUtils.isEmpty(emailRegi)){
                    emailRegister.setError("Fill up the required field!");
                    return;
                }
                if(TextUtils.isEmpty(passRegi)){
                    passwordRegister.setError("Fill up the required field!");
                    return;
                }

                mDialog.setMessage("Processing..");
                mDialog.show();

                mAuth.createUserWithEmailAndPassword(emailRegi,passRegi).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"successfully created",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                        mDialog.dismiss();
                        }else{
                            Toast.makeText(getApplicationContext(),"Register failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });


    }
}