package com.mailsender.settings;

import java.io.Serializable;

public class EmailSettings implements Serializable{
    public String password;
    public String login;
    public String smtp;
    public Integer port;
}
