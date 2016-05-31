package com.mailsender.service;


import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;
import org.codemonkey.simplejavamail.email.Email;

public class MailSender {

    public static void sendMail (String emailAddres, String text) {


        Email email = new Email.Builder()
                .from("stiksmarket", "stiksmarket@gmail.com")
                .to("", emailAddres)
                .subject("test")
                .textHTML(text)
                .build();

        new Mailer("smtp.gmail.com", 465, "", "", TransportStrategy.SMTP_SSL).sendMail(email);
        System.out.println(emailAddres + " ---  SENT");
    }
}
