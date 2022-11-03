package me.tsaheylu.exception;

public class EmailAlreadyVerifiedException extends RuntimeException {

    public EmailAlreadyVerifiedException(String token, String message) {
        super(String.format("Failed for [%s]: %s", token, message));
    }
}
