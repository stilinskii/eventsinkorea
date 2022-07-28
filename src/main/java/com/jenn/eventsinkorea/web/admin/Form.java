package com.jenn.eventsinkorea.web.admin;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class Form {
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
    private String join_date;
}
