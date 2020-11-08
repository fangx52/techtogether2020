package com.example.techtogether2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

public class CreateGroup extends AppCompatActivity {
    private Button btnDashboard, btnCreateGroup;
    private String chatID, interest;
    private FirebaseAuth auth;
    private EditText txtGroupName;
    private Spinner spInterests;
    private ArrayList<String> allInterests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        Random rand = new Random();
        chatID = rand.nextInt(300)+"";
        spInterests= (Spinner) findViewById(R.id.spInterests);
        allInterests = new ArrayList<>();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.interests_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spInterests.setAdapter(adapter);

        btnCreateGroup = (Button) findViewById(R.id.btnCreateGroup);
        txtGroupName = (EditText) findViewById(R.id.txtGroupName);

        spInterests.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                InputMethodManager keyboard = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                interest = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth = FirebaseAuth.getInstance();
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
                FirebaseUser user= auth.getCurrentUser();
                String groupName = txtGroupName.getText().toString();
                ChatInfo info = new ChatInfo( chatID, R.drawable.profilepic2,groupName ,"Topic:" +interest +" | "+
                        "How's it going...");

                databaseReference.child(user.getUid()).child("Group").child(groupName).setValue(info);
                Intent intent = new Intent(CreateGroup.this, AllChat.class);
                intent.putExtra("Chat", chatID);
                intent.putExtra("Group", groupName);
                startActivity(intent);
                finish();

            }
        });

        btnDashboard = (Button) findViewById(R.id.btnDashboard);
        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CreateGroup.this,Dashboard.class);
                startActivity(intent);
            }
        });

  }
}