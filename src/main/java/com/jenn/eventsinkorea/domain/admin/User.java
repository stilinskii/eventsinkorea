package com.jenn.eventsinkorea.domain.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private Integer id;

    private String user_id;
    private String pwd;
    private String username;
    private String email;
    private String nationality;
    private String join_date;
}
