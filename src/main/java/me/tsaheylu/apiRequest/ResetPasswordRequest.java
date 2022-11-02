package me.tsaheylu.apiRequest;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class ResetPasswordRequest {

    @NotEmpty
    private String email;

}
