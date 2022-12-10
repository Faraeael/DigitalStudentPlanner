package com.example.digitalstudentplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    TextView LoginAccount;
    EditText inputEmail,inputPassword,inputUsername;
    Button btnRegister;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        LoginAccount=findViewById(R.id.btnLogInNow);
        inputEmail=findViewById(R.id.email);
        inputPassword=findViewById(R.id.password);
        inputUsername=findViewById(R.id.username);
        btnRegister=findViewById(R.id.registerBtn);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        LoginAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,LogIn.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perforAuth();
            }
        });

    }

    private void perforAuth() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String username = inputUsername.getText().toString();

        if(email.isEmpty()){
            inputEmail.setError("Enter Proper Email");
        }else if (password.isEmpty()){
            inputPassword.setError("Enter Password");
        }else if (username.isEmpty()){
            inputUsername.setError("Please input Username");
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        sendUsertoNextActivity();
                        Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Register.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUsertoNextActivity() {
        Intent intent = new Intent(Register.this,LogIn.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}