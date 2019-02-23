package com.maslke.spring.spel;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private int credits;

    private boolean gender;

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    private List<String> hobbies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public User(String name, int credits) {
        this.name = name;
        this.credits = credits;
        this.hobbies = new ArrayList<String>();
        this.gender = true;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
