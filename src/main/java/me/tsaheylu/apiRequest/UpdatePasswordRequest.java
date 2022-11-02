package me.tsaheylu.apiRequest;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
public class UpdatePasswordRequest {

    @Size(min = 6, message = "{Size.userDto.password}")
    private String password;

    @NotEmpty
    private String token;

}
