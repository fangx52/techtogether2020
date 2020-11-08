package com.example.techtogether2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MainActivity extends AppCompatActivity {
    private Button btnSignup, btnSignin;
    private EditText txtEmail, txtPassword;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnSignin = (Button) findViewById(R.id.btnSignin);
        btnSignup = (Button) findViewById(R.id.btnSignup);


        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                if (email.equals("") || email.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                }else if(password.equals("")||password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                }else {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                txtEmail.setText("");
                                txtPassword.setText("");
                                Toast.makeText(MainActivity.this, "Wrong Email/Password", Toast.LENGTH_SHORT).show();


                            } else {
                                NavigatetoDashboard();
                               }
                        }
                    });
                    InputMethodManager keyboard = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    keyboard.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                }
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigatetoSignUp();
            }
        });
    }
    public void NavigatetoDashboard(){
        Intent intent = new Intent(MainActivity.this, Dashboard.class);
        intent.putExtra("user", auth.getCurrentUser());
        startActivity(intent);
        finish();

    }
    public void NavigatetoSignUp(){
        Intent intent = new Intent(MainActivity.this, Signup.class);
        startActivity(intent);
        finish();

    }
}
