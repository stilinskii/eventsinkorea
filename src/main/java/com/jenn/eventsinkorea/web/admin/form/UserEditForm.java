package com.jenn.eventsinkorea.web.admin.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class UserEditForm {

    private Long id;

    @NotNull
    @Size(min=5,max=30)
    private String username;

    @NotNull
    @Size(min=2,max=30)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String nationality;
    private String joinDate;
}
