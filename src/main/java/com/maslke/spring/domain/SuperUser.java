package com.maslke.spring.domain;

import com.maslke.spring.annotation.Super;

@Super
public class SuperUser extends User {
    private String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SuperUser[id=" + getId() + ",name=" + getName() + ",email=" + getEmail() + "]";
    }
}
