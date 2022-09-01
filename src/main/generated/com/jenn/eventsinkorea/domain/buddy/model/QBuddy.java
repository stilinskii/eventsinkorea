package com.jenn.eventsinkorea.domain.buddy.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBuddy is a Querydsl query type for Buddy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBuddy extends EntityPathBase<Buddy> {

    private static final long serialVersionUID = 1055282743L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBuddy buddy = new QBuddy("buddy");

    public final ListPath<BuddyRequest, QBuddyRequest> buddyRequests = this.<BuddyRequest, QBuddyRequest>createList("buddyRequests", BuddyRequest.class, QBuddyRequest.class, PathInits.DIRECT2);

    public final ListPath<BuddyReview, QBuddyReview> buddyReviews = this.<BuddyReview, QBuddyReview>createList("buddyReviews", BuddyReview.class, QBuddyReview.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final StringPath intro = createString("intro");

    public final StringPath learningLang = createString("learningLang");

    public final NumberPath<Long> likeCnt = createNumber("likeCnt", Long.class);

    public final ListPath<com.jenn.eventsinkorea.domain.user.User, com.jenn.eventsinkorea.domain.user.QUser> likedUsers = this.<com.jenn.eventsinkorea.domain.user.User, com.jenn.eventsinkorea.domain.user.QUser>createList("likedUsers", com.jenn.eventsinkorea.domain.user.User.class, com.jenn.eventsinkorea.domain.user.QUser.class, PathInits.DIRECT2);

    public final StringPath location = createString("location");

    public final StringPath nativeLang = createString("nativeLang");

    public final NumberPath<Double> rating = createNumber("rating", Double.class);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final com.jenn.eventsinkorea.domain.user.QUser user;

    public QBuddy(String variable) {
        this(Buddy.class, forVariable(variable), INITS);
    }

    public QBuddy(Path<? extends Buddy> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBuddy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBuddy(PathMetadata metadata, PathInits inits) {
        this(Buddy.class, metadata, inits);
    }

    public QBuddy(Class<? extends Buddy> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.jenn.eventsinkorea.domain.user.QUser(forProperty("user")) : null;
    }

}

