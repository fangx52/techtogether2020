package com.example.techtogether2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AllChat extends AppCompatActivity {
    private TextView txtVwName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_chat);
        Intent intent = getIntent();
        String ID = intent.getStringExtra("ID");
        String groupName = intent.getStringExtra("Group");
        txtVwName = (TextView) findViewById(R.id.txtVwName);
        txtVwName.setText(groupName);

    }
}