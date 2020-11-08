package com.example.techtogether2020;

import java.util.ArrayList;
import java.util.HashSet;

public class UserInfo {
    public String name;
    public HashSet<String> interests;
    public String existingGroup;
    public HashSet<String> groupTracker;

//    public UserInfo(String name, String interests){
public UserInfo(String name, String existingGroup, HashSet<String> interests){
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
    public HashSet<String> getInterests(){
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
