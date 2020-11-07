package com.example.techtogether2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    private Spinner  spInterests;
    private EditText txtEmail, txtPassword, txtName, txtGroup;
    private Button btnSignin, btnSignup;

    private FirebaseAuth auth;
    private String TAG= "COMPLETE SIGNIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        auth = FirebaseAuth.getInstance();


        txtName = (EditText) findViewById(R.id.txtName);
        txtEmail= (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        spInterests =(Spinner) findViewById(R.id.spInterests);
        txtGroup = (EditText) findViewById(R.id.txtGroup);


        btnSignin =(Button) findViewById(R.id.btnSignin);
        btnSignup = (Button) findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String password= txtPassword.getText().toString();
                String name = txtName.getText().toString();
                String group= txtGroup.getText().toString();
                if (email.equals("") || password.equals("")){
                    txtEmail.setText("");
                    txtPassword.setText("");
                    Toast.makeText(Signup.this, "Wrong Email/Password", Toast.LENGTH_SHORT).show();

                }else {
                    createUser(name, email, password, group);
                    NavigatetoDashboard();
                }
                InputMethodManager keyboard = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigatetoSignIn();
            }
        });


    }
    public void createUser(String name, String email, String password, String group){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = auth.getCurrentUser();
                    //updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(Signup.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        FirebaseUser user= auth.getCurrentUser();
        UserInfo info = new UserInfo(name, group);

        databaseReference.child(user.getUid()).setValue(info);

    }
    public void NavigatetoSignIn(){
        Intent intent = new Intent(Signup.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
    public void NavigatetoDashboard(){
        Intent intent = new Intent(Signup.this, Dashboard.class);
        startActivity(intent);
        finish();

    }

}