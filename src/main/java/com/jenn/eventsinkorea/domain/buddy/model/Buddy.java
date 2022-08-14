package com.jenn.eventsinkorea.domain.buddy.model;

import com.jenn.eventsinkorea.domain.user.User;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "buddies")
public class Buddy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "native_lang")
    private String nativeLang;
    @Column(name = "learning_lang")
    private String learningLang;
    private String location;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "img_url")
    private String imgUrl;
    private String intro;

    @OneToOne
    @JoinColumn(name = "user_id")
//    @JoinTable(name = "buddies_users",
//            joinColumns = @JoinColumn(name = "buddy_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    public Buddy() {
    }

    public Buddy(String nativeLang, String secondLang, String location, String img, String intro, User user) {
        this.nativeLang = nativeLang;
        this.learningLang = secondLang;
        this.location = location;
        this.imgUrl = img;
        this.intro = intro;
        this.user = user;
    }
}
