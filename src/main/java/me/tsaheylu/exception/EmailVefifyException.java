package me.tsaheylu.exception;

public class EmailVefifyException extends RuntimeException {

    public EmailVefifyException(String email, String message) {
        super(String.format("Failed for [%s]: %s", email, message));
    }
}
