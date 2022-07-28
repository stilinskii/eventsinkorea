package com.jenn.eventsinkorea.domain.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private Integer id;

    @NotNull
    @Size(min=5,max=30)
    @Column(name = "user_id")
    private String userId;

    @NotNull
    //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String pwd;

    @NotNull
    @Size(min=2,max=30)
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String nationality;
    private Date join_date;

    public User(String user_id, String pwd, String username, String email, String nationality) {
        this.userId = user_id;
        this.pwd = pwd;
        this.username = username;
        this.email = email;
        this.nationality = nationality;
    }
}
