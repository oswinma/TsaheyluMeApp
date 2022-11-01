package me.tsaheylu.service;


import me.tsaheylu.model.User;


public interface EmailService {


    void sendSimpleMessage();


    void sendEmailVerificationEmail(User user);
}
