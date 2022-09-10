package com.jenn.eventsinkorea.web.account.form;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserLoginForm {
    @NotBlank
    @Size(min=5,max=30)
    private String username;

    @NotBlank
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String pwd;

    @NotBlank
    @NotNull
    @Size(min=2,max=30)
    private String name;

    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotBlank
    @NotNull
    private String nationality;
}
