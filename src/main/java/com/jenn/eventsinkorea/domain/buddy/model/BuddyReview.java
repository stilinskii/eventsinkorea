package com.jenn.eventsinkorea.domain.buddy.model;

import com.jenn.eventsinkorea.domain.user.model.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "buddy_review")
@Getter @Setter
public class BuddyReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buddy_id")
    private Buddy buddy;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating;
    private String comment;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @Transient
    private String formattedCreatedAt;

    public String getFormattedCreatedAt() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(createdAt);
    }

}
