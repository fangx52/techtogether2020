package com.example.techtogether2020;

public class ChatInfo {
    private int mProfilePic;
    private String mName;
    private String mMessage;
    private String chatID;

    ChatInfo(int profilePic, String name, String message, String chatID) {
        mProfilePic = profilePic;
        mName = name;
        mMessage = message;
        this.chatID= chatID;
    }

    public String getChatID(){
        return chatID;
    }
    public int getProfilePic() {
        return mProfilePic;
    }

    public String getName() {
        return mName;
    }

    public String getMessage() {
        return mMessage;
    }
}


