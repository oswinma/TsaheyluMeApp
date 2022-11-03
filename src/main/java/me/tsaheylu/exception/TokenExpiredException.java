package me.tsaheylu.exception;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException(String token, String message) {
        super(String.format("Failed for [%s]: %s", token, message));
    }
}
