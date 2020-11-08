package com.example.techtogether2020;

public class ChatInfo {
    private int mProfilePic;
    private String mName;
    private String mMessage;

    ChatInfo(int profilePic, String name, String message) {
        mProfilePic = profilePic;
        mName = name;
        mMessage = message;
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


