package me.tsaheylu.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String email, String message) {
        super(String.format("Failed for [%s]: %s", email, message));
    }
}
