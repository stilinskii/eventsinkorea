package com.jenn.eventsinkorea.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.jenn.eventsinkorea.domain.user.model.Role;
import com.jenn.eventsinkorea.domain.user.model.User;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1346170860L;

    public static final QUser user = new QUser("user");

    public final ListPath<com.jenn.eventsinkorea.domain.buddy.model.Buddy, com.jenn.eventsinkorea.domain.buddy.model.QBuddy> buddyILike = this.<com.jenn.eventsinkorea.domain.buddy.model.Buddy, com.jenn.eventsinkorea.domain.buddy.model.QBuddy>createList("buddyILike", com.jenn.eventsinkorea.domain.buddy.model.Buddy.class, com.jenn.eventsinkorea.domain.buddy.model.QBuddy.class, PathInits.DIRECT2);

    public final ListPath<com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest, com.jenn.eventsinkorea.domain.buddy.model.QBuddyRequest> buddyRequests = this.<com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest, com.jenn.eventsinkorea.domain.buddy.model.QBuddyRequest>createList("buddyRequests", com.jenn.eventsinkorea.domain.buddy.model.BuddyRequest.class, com.jenn.eventsinkorea.domain.buddy.model.QBuddyRequest.class, PathInits.DIRECT2);

    public final ListPath<com.jenn.eventsinkorea.domain.buddy.model.BuddyReview, com.jenn.eventsinkorea.domain.buddy.model.QBuddyReview> buddyReviews = this.<com.jenn.eventsinkorea.domain.buddy.model.BuddyReview, com.jenn.eventsinkorea.domain.buddy.model.QBuddyReview>createList("buddyReviews", com.jenn.eventsinkorea.domain.buddy.model.BuddyReview.class, com.jenn.eventsinkorea.domain.buddy.model.QBuddyReview.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> joinedDate = createDateTime("joinedDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final StringPath nationality = createString("nationality");

    public final StringPath pwd = createString("pwd");

    public final ListPath<Role, QRole> roles = this.<Role, QRole>createList("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

