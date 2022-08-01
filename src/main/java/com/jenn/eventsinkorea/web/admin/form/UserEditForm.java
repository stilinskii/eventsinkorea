package com.jenn.eventsinkorea.web.admin.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserEditForm {
    @NotNull
    @Size(min=5,max=30)
    private String userId;

    @NotNull
    @Size(min=2,max=30)
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String nationality;
    private String joinDate;
}
