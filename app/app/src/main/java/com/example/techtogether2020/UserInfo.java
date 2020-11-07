package com.example.techtogether2020;

public class UserInfo {
    public String name;
    public String interests;
    public String existingGroup;

//    public UserInfo(String name, String interests){
public UserInfo(String name, String existingGroup){
        this.name= name;
        this.existingGroup = existingGroup;
        this.interests= interests;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name= name;
    }
    public String getInterests(){
        return this.interests;
    }
    public void setInterests(String name){
        this.interests= interests;
    }
    public String getExistingGroup(){
        return this.existingGroup;
    }
    public void setExistingGroup(String name){
        this.existingGroup= existingGroup;
    }
}
