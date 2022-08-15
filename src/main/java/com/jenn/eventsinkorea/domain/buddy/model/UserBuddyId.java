package com.jenn.eventsinkorea.domain.buddy.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserBuddyId implements Serializable {

    @Column(name="user_id")
    private Long userId;
    @Column(name = "buddy_id")
    private Long buddyId;
}
