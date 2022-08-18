package com.jenn.eventsinkorea.domain.user;

import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest;
import com.jenn.eventsinkorea.domain.user.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
    private String username;

    @NotNull
    //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String pwd;

    @NotNull
    @Size(min=2,max=30)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String nationality;

    @Column(name = "joined_date")
    private Date joinedDate;

    @ManyToMany(mappedBy = "users")
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<BuddyRequest> buddyRequests;

    @ManyToMany(mappedBy = "likedUsers")
    private List<Buddy> buddyILike;


    public User(String user_id, String pwd, String username, String email, String nationality) {
        this.username = user_id;
        this.pwd = pwd;
        this.name = username;
        this.email = email;
        this.nationality = nationality;
    }
}
