package com.jenn.eventsinkorea.domain.buddy.model;

import com.jenn.eventsinkorea.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
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
    private User user; //신청자

    @ManyToOne
    @MapsId("buddyId")
    private Buddy buddy; // 신청받은자

    private int status;//0:대기 1:수락 2:거절

    @Column(name = "request_date")
    private Date requestDate;

    @Transient
    private String formattedRequestDate;

    public String getFormattedRequestDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(requestDate);
    }
}
