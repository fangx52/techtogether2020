package com.example.techtogether2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AllChat extends AppCompatActivity {
    private TextView txtVwName;
    private String ID;
    private String groupName;
    private Button btnAllMemebers, btnTeam1, btnTeam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_chat);
        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");
        groupName = intent.getStringExtra("Group");
        txtVwName = (TextView) findViewById(R.id.txtVwName);
        txtVwName.setText(groupName);
        btnAllMemebers=(Button) findViewById(R.id.btnAllMembers);
        btnTeam1=(Button) findViewById(R.id.btnTeam1);
        btnTeam2 = (Button) findViewById(R.id.btnTeam2);
        btnAllMemebers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigatetoChat("All Members");

            }
        });
        btnTeam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigatetoChat("Team1");
            }
        });
        btnTeam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigatetoChat("Team2");
            }
        });

    }
    public void NavigatetoChat(String channel){
        Intent intent = new Intent(AllChat.this, Chat.class);
        intent.putExtra("Chat", ID);
        intent.putExtra("Group", groupName);
        intent.putExtra("Channel", channel);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(AllChat.this, Dashboard.class);
            intent.putExtra("Chat", ID);
            intent.putExtra("Group", groupName);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}