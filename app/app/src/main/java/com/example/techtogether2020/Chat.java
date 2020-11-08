package com.example.techtogether2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Chat extends AppCompatActivity {
    FirebaseAuth auth;
    private String ID;
    private String groupName;
    private String channel;
    private TextView txtVwChatGrp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");
        groupName = intent.getStringExtra("Group");
        channel=intent.getStringExtra("Channel");
        txtVwChatGrp=(TextView) findViewById(R.id.txtVwChatGrp);
        txtVwChatGrp.setText(channel);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Chat.this, AllChat.class);
            intent.putExtra("Chat", ID);
            intent.putExtra("Group", groupName);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}