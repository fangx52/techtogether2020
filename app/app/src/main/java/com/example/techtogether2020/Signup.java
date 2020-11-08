package com.example.techtogether2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashSet;

public class Signup extends AppCompatActivity {
    private EditText txtEmail, txtPassword, txtName, txtGroup;
    private Button btnSignin, btnSignup;
    private Spinner spInterests;
    private LinearLayout lnInterests;
    private HashSet<String> allInterests;

    private FirebaseAuth auth;
    private String TAG= "COMPLETE SIGNIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        auth = FirebaseAuth.getInstance();


        lnInterests = (LinearLayout) findViewById(R.id.lnInterests);
        txtName = (EditText) findViewById(R.id.txtName);
        txtEmail= (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtGroup = (EditText) findViewById(R.id.txtGroup);
        spInterests = (Spinner) findViewById(R.id.spInterests);
        allInterests = new HashSet<>();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.interests_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spInterests.setAdapter(adapter);
        spInterests.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                InputMethodManager keyboard = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                String selected = parent.getSelectedItem().toString();
                if (!selected.equals("") || !selected.isEmpty()) {
                    allInterests.add(selected);
                    TextView interests = new TextView(Signup.this);
                    interests.setBackground(getResources().getDrawable(R.drawable.interests));
                    interests.setText(selected);
                    interests.setGravity(Gravity.CENTER);
                    interests.setTextColor(Color.BLACK);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 75);
                    layoutParams.setMargins(10, 0, 10, 0);
                    lnInterests.addView(interests, layoutParams);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                    createUser(name, email, password, group, allInterests);

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
    public void createUser(final String name, String email, String password, final String group, final HashSet<String> interests){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = auth.getCurrentUser();
                    UserInfo info = new UserInfo(name, group, interests);
                    databaseReference.child(user.getUid()).setValue(info);
                    Log.i("UID", user.getUid());
                    NavigatetoDashboard();

                    //updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(Signup.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void NavigatetoSignIn() {
        Intent intent = new Intent(Signup.this, MainActivity.class);
        intent.putExtra("user", auth.getCurrentUser());
        startActivity(intent);
        finish();
    }
    public void NavigatetoDashboard() {
        Intent intent = new Intent(Signup.this, Dashboard.class);
        startActivity(intent);
        finish();
    }

}

