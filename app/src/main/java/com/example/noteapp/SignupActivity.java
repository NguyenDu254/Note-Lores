package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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

public class SignupActivity extends AppCompatActivity {
    TextView txtSignIn, txtBack;
    EditText edtFullName, edtEmail, edtPhone, edtPassword, edtComfirmPassword;
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
        setContentView(R.layout.activity_signup);

        Button btnRegister = findViewById(R.id.btnRegister);
        edtFullName = findViewById(R.id.edtFullName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        edtComfirmPassword = findViewById(R.id.edtComfirmPassword);
        txtSignIn = findViewById(R.id.txtSignUp);
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        txtBack = findViewById(R.id.txtBack);
        txtBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, email, phone, password, comfirmpassword;
                username = String.valueOf(edtFullName.getText());
                email = String.valueOf(edtEmail.getText());
                phone = String.valueOf(edtPhone.getText());
                password = String.valueOf(edtPassword.getText());
                comfirmpassword = String.valueOf(edtComfirmPassword.getText());
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(SignupActivity.this, "Enter UserName", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignupActivity.this, "Enter Email", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(SignupActivity.this, "Enter Phone", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignupActivity.this, "Enter Password", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(comfirmpassword)){
                    Toast.makeText(SignupActivity.this, "Enter ConfirmPassword", Toast.LENGTH_LONG).show();
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(SignupActivity.this, "SignUp Successfully", Toast.LENGTH_LONG).show();
//                                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
//                                    startActivity(intent);
//                                    finish();
                                }else{
                                    Toast.makeText(SignupActivity.this, "SignUp Failed", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
//                try {
//                    if(edtFullName.getText().toString().equals("") || edtEmail.getText().toString().equals("")  ||
//                            edtPhone.getText().toString().equals("")  || edtPassword.getText().toString().equals("")  ||
//                            edtComfirmPassword.getText().toString().equals("") )
//                        throw new Exception("Không được để trống!!!");
//                    if(!edtPassword.getText().toString().equals(edtComfirmPassword.getText().toString()))
//                        throw  new Exception("Password không giống nhau!!!");
//
//                    User newUser = new User(edtFullName.getText().toString(), edtEmail.getText().toString(),
//                            edtPhone.getText().toString(), edtPassword.getText().toString());
//
//                    for(User u : User.userList){
////                        if(u.UserName == newUser.UserName || u.Email == newUser.Email || u.Phone == newUser.Phone){
////                            throw new Exception("Đã tồn tại Username hoặc Email hoặc Phone!!!");
////                        }
//                        if(u.UserName.equals(newUser.UserName) || u.Email.equals(newUser.Email) || u.Phone.equals(newUser.Phone)){
//                            throw new Exception("Đã tồn tại Username hoặc Email hoặc Phone!!!");
//                        }
//
//                    }
//                    User.userList.add(newUser);
//                    Toast.makeText(SignupActivity.this, "Đăng ký thành công!!!", Toast.LENGTH_LONG).show();
//                }catch (Exception e){
//                    new AlertDialog.Builder(SignupActivity.this)
//                            .setMessage(e.getMessage())
//                            .setPositiveButton("OK", null)
//                            .show();
//                }
            }
        });
    }
}