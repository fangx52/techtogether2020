package com.example.techtogether2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageButton;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    private FirebaseAuth auth;
    private ArrayList<ChatInfo> chats;
    private ImageButton imgBtnAddGroup;
    private String TAG="DATABASE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        imgBtnAddGroup = (ImageButton) findViewById(R.id.imgBtnAddGroup);
        String chatID = "1", chatID1 = "2";
        auth = FirebaseAuth.getInstance();
        FirebaseUser user= auth.getCurrentUser();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference(user.getUid());

        ChatInfo info = new ChatInfo(R.drawable.profilepic2, "Group 1", "Alexa! Where are you? " +
                "How's it going...", chatID);
        ChatInfo info1 = new ChatInfo(R.drawable.profilepic1, "Group 2", "Mary! Where are you? " +
                "How's it going...", chatID1);
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });

        databaseReference.child("Group").child("Group 1").setValue(info);
        databaseReference.child("Group").child("Group 2").setValue(info1);


        chats = new ArrayList<>();
        chats.add(info);
        chats.add(info1);
        ChatAdapter adapter = new ChatAdapter(this, chats);

        ListView chatList = (ListView) findViewById(R.id.listview_chats);

        chatList.setAdapter(adapter);
        chatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                NavigatetoAllChat(chats.get(position).getChatID(),chats.get(position).getName());

                imgBtnAddGroup = (ImageButton) findViewById(R.id.imgBtnAddGroup);
                imgBtnAddGroup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Dashboard.this, CreateGroup.class);
                        startActivity(intent);
                    }
                });

            }
        });
        imgBtnAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigatetoCreateGroup();
            }
        });
    }

    private void NavigatetoAllChat(String chatID, String groupName) {
        Intent intent = new Intent(Dashboard.this, AllChat.class);
        intent.putExtra("Chat", chatID);
        intent.putExtra("Group", groupName);
        startActivity(intent);
        finish();

    }
    private void NavigatetoCreateGroup() {
        Intent intent = new Intent(Dashboard.this, CreateGroup.class);
        startActivity(intent);
        finish();

    }
}

class ChatAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<ChatInfo> chats;
    private final Resources res;

    public ChatAdapter(Context context, ArrayList<ChatInfo> chats) {
        this.context = context;
        this.chats = chats;
        this.res = context.getResources();

    }

    @Override
    public int getCount() {
        return chats.size();
    }

    @Override
    public ChatInfo getItem(int pos) {
        return chats.get(pos);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent) {
        //Get the instance of our chat
        View row;
        if (view == null) {  //indicates this is the first time we are creating this row.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  //Inflater's are awesome, they convert xml to Java Objects!
            row = inflater.inflate(R.layout.customchat, parent, false);
        } else {
            row = view;
        }
        ChatInfo chat = chats.get(position);
        Log.i("CHATINFO", chat.getName());
        Log.i("CHATINFO", chat.getMessage());


        //Get UI objects
        ImageView profilePic = (ImageView) row.findViewById(R.id.imgVwProfile);
        TextView nameView = (TextView) row.findViewById(R.id.txtVwName);
        TextView messageView = (TextView) row.findViewById(R.id.txtVwMessage);

        //Set image profile picture
        profilePic.setImageDrawable(res.getDrawable(chat.getProfilePic()));

        //Set text into TextViews
        nameView.setText(chat.getName());
        messageView.setText(chat.getMessage());

        return row;
    }

}