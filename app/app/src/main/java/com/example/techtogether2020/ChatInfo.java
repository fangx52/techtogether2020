package com.example.techtogether2020;

public class ChatInfo {
    private int profilePic;
    private String name;
    private String message;
    private String chatID;
    public ChatInfo( ) {

    }
    public ChatInfo( String chatID,int profilePic, String name,String message) {
        this.profilePic = profilePic;
        this.name = name;
        this.message = message;
        this.chatID= chatID;
    }


    public String getChatID(){
        return chatID;
    }
    public int getProfilePic() {
        return profilePic;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}


