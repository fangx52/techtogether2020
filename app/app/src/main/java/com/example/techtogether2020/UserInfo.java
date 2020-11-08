package com.example.techtogether2020;

import java.util.ArrayList;

public class UserInfo {
    public String name;
    public ArrayList<String> interests;
    public String existingGroup;

//    public UserInfo(String name, String interests){
public UserInfo(String name, String existingGroup, ArrayList<String> interests){
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
    public ArrayList<String> getInterests(){
        return this.interests;
    }
    public void setInterests(String name){
        interests.add(name);
    }
    public String getExistingGroup(){
        return this.existingGroup;
    }
    public void setExistingGroup(String name){
        this.existingGroup= existingGroup;
    }
}
