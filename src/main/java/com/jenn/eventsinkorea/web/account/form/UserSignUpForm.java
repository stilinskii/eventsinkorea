package com.jenn.eventsinkorea.web.account.form;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserSignUpForm {
    @NotNull
    @Size(min=5,max=30)
    private String username;


    @NotNull
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}")
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
