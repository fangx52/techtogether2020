package com.example.techtogether2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(AllChat.this, Dashboard.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}