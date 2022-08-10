package com.jenn.eventsinkorea.domain.admin.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Builder
@Entity
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "joined_date")
    private Date joinedDate;

    public User(String user_id, String pwd, String username, String email, String nationality) {
        this.userId = user_id;
        this.pwd = pwd;
        this.username = username;
        this.email = email;
        this.nationality = nationality;
    }
}
