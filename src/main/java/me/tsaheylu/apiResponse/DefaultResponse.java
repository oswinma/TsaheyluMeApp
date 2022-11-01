package me.tsaheylu.apiResponse;

import lombok.Value;

@Value
public class DefaultResponse {
    private Boolean success;
    private String message;
}