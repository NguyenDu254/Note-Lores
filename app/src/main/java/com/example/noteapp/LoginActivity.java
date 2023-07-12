package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LoginActivity extends AppCompatActivity {

    TextView txtSignUp,txtBack;
    EditText edtEmail, edtPassword;
    Button btnSignIn;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currenUser = firebaseAuth.getCurrentUser();
        if(currenUser!=null){
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        txtSignUp = findViewById(R.id.txtSignIn);
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        txtBack = findViewById(R.id.txtBack);
        txtBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = String.valueOf(edtEmail.getText());
                password = String.valueOf(edtPassword.getText());
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this, "Enter Email", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Enter Password", Toast.LENGTH_LONG).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this,"Login Successful!!!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(LoginActivity.this, "Login Failed, Please Check Your Password Or Email. :(", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}