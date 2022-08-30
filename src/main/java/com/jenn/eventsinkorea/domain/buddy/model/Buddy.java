package com.jenn.eventsinkorea.domain.buddy.model;

import com.jenn.eventsinkorea.domain.user.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "buddies")
@ToString
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
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @CreationTimestamp
    private Date updatedAt;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name="file_name")
    private String fileName;

    private String intro;

    @Column(name = "like_cnt")
    @ColumnDefault("0")
    private Long likeCnt;

    private Double rating;
    @OneToOne
    @JoinColumn(name = "user_id")
//    @JoinTable(name = "buddies_users",
//            joinColumns = @JoinColumn(name = "buddy_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    @OneToMany(mappedBy = "buddy")
    private List<BuddyRequest> buddyRequests;

    @ManyToMany
    @JoinTable(
            name = "buddy_user_like",
            joinColumns = @JoinColumn(name = "buddy_id"),
            inverseJoinColumns = @JoinColumn(name = "user_username"))
    private List<User> likedUsers;


    @OneToMany(mappedBy = "buddy")
    private List<BuddyReview> buddyReviews = new ArrayList<>();


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
