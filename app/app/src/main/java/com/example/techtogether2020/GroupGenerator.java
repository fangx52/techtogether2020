package com.example.techtogether2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class GroupGenerator {

    public ArrayList<TreeMap<Integer, UserInfo>> findCompatibilityScores(ArrayList<UserInfo> people) {
        ArrayList<TreeMap<Integer, UserInfo>> compatability = new ArrayList<>();
        for (int i = 0; i < people.size(); i++) {
            compatability.add(new TreeMap<Integer, UserInfo>());
            for (int j = 0; j < people.size(); j++) {
                UserInfo user1 = people.get(i);
                UserInfo user2 = people.get(j);
                if (user1.equals(user2)) {
                    compatability.get(i).put(-1, user2);
                }
                if (user1.groupTracker.contains(user2) || user2.groupTracker.contains(user1)) {
                    compatability.get(i).put(0, user2);
                }
                else {
                    compatability.get(i).put(getCompatability(user1, user2), user2);
                }
            }
        }
        return compatability;
    }

    private int getCompatability(UserInfo u1, UserInfo u2) {
        int count = 1;
        for (String item : u2.getInterests()) {
            if (u1.getInterests().contains(item)) {
                count += 1;
            }
        }
        return count;
    }

    public ArrayList<ArrayList<String>> generateGroups(ArrayList<TreeMap<Integer, UserInfo>> sortedPeople, int grpSize) {
        ArrayList<ArrayList<String>> newGroups = new ArrayList<>();
        HashSet<String> placed = new HashSet<>();
        for (int i = 0; i < sortedPeople.size(); i++) {
            newGroups.add(new ArrayList<String>());
            UserInfo firstPerson = sortedPeople.get(i).lastEntry().getValue();
            for (int j = 0; j < grpSize; j++) {
                String entry = sortedPeople.get(i).pollLastEntry().getValue().getName();
                if (!placed.contains(entry)) {
                    newGroups.get(i).add(entry);
                    placed.add(entry);
                    firstPerson.groupTracker.add(entry);
                }
            }
        }
        return newGroups;
    }

}
