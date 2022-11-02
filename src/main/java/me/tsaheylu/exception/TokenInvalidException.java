package me.tsaheylu.exception;

public class TokenInvalidException extends RuntimeException {

    public TokenInvalidException(String token, String message) {
        super(String.format("Failed for [%s]: %s %s", token, message));
    }
}
