package com.jenn.eventsinkorea.domain.buddy.model;

import com.jenn.eventsinkorea.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BuddyRequest {

    //완전헷갈려

    @EmbeddedId
    private UserBuddyId userBuddyId = new UserBuddyId();

    @ManyToOne
    @MapsId("userId")
//    @JoinColumn(name = "user_id", nullable = false)
    private User user; //신청자

    @ManyToOne
    @MapsId("buddyId")
//    @JoinColumn(name = "buddy_id")
    private Buddy buddy; // 신청받은자

    private int status;//0:대기 1:수락 2:거절

    @Column(name = "request_date")
    private Date requestDate;

}
