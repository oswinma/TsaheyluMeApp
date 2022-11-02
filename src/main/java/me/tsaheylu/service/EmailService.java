package me.tsaheylu.service;


import me.tsaheylu.model.User;
import org.springframework.scheduling.annotation.Async;


public interface EmailService {


    void sendSimpleMessage();


    void sendEmailVerificationEmail(User user);

    @Async
    void sendResetPasswordEmail(User user);
}
