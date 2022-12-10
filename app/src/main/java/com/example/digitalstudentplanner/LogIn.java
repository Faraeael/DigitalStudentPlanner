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

public class LogIn extends AppCompatActivity {

    TextView createNewAccount;
    EditText inputEmail,inputPassword;
    Button btnLogin;


    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        createNewAccount = findViewById(R.id.btnRegisterNow);
        inputEmail=findViewById(R.id.email);
        inputPassword=findViewById(R.id.password);
        btnLogin=findViewById(R.id.loginBtn);

        createNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LogIn.this,Register.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                perforLogin();
            }
        });
    }

    private void perforLogin() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        if(email.isEmpty()){
            inputEmail.setError("Enter Proper Email");
        }else if (password.isEmpty()){
            inputPassword.setError("Enter Password");
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        sendUsertoNextActivity();
                        Toast.makeText(LogIn.this, "Logged In", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LogIn.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUsertoNextActivity() {
        Intent intent = new Intent(LogIn.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}