package com.example.techtogether2020;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        String email = user.getEmail();
        Log.i("UserInfo", email);


        ArrayList<ChatInfo> chats = new ArrayList<>();
        chats.add(new ChatInfo(R.drawable.profileimg, "Hermione Granger", "Harry! Where are you? " +
                "How's it going..."));
        ChatAdapter adapter = new ChatAdapter(this, R.id.lnDashboard, chats);

        ListView chatList = (ListView) findViewById(R.id.lnDashboard);

        chatList.setAdapter(adapter);

    }
}

class ChatAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<ChatInfo> mChats;
    private final Resources mRes;

    public ChatAdapter(Context context, ArrayList<ChatInfo> chats) {
        this.context = context;
        mChats = chats;
        mRes = context.getResources();

    }

    @Override
    public int getCount() {
        return mChats.size();
    }

    @Override
    public ChatInfo getItem(int pos) {
        return mChats.get(pos);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent) {
        //Get the instance of our chat
        View row;
        if (view == null){  //indicates this is the first time we are creating this row.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  //Inflater's are awesome, they convert xml to Java Objects!
            row = inflater.inflate(R.layout.customchat, parent, false);
        }
        else
        {
            row = view;
        }
        ChatInfo chat = mChats.get(position);


        //Get UI objects
        ImageView profilePic = (ImageView) row.findViewById(R.id.imgVwProfile);
        TextView nameView = (TextView) row.findViewById(R.id.txtVwName);
        TextView messageView = (TextView) row.findViewById(R.id.message);

        //Set image profile picture
        profilePic.setImageDrawable(mRes.getDrawable(chat.getProfilePic()));

        //Set text into TextViews
        nameView.setText(chat.getName());
        messageView.setText(chat.getMessage());

        return row;
    }

}