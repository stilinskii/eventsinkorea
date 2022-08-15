package com.jenn.eventsinkorea.web.account.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserLoginForm {
    @NotNull
    @Size(min=5,max=30)
    private String username;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String pwd;

    @NotNull
    @Size(min=2,max=30)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String nationality;
}
