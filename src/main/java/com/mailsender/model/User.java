package com.mailsender.model;

import java.io.Serializable;

public class User implements Serializable{

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
