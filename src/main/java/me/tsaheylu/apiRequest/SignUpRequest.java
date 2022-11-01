package me.tsaheylu.apiRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import lombok.Data;


@Data
public class SignUpRequest {


    @NotEmpty
    private String nickName;

    @NotEmpty
    private String email;

    @Size(min = 6, message = "{Size.userDto.password}")
    private String password;

}
