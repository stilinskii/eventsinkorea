package com.jenn.eventsinkorea.domain.buddy.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBuddyRequest is a Querydsl query type for BuddyRequest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBuddyRequest extends EntityPathBase<BuddyRequest> {

    private static final long serialVersionUID = -313190312L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBuddyRequest buddyRequest = new QBuddyRequest("buddyRequest");

    public final QBuddy buddy;

    public final DateTimePath<java.util.Date> requestDate = createDateTime("requestDate", java.util.Date.class);

    public final NumberPath<Integer> review = createNumber("review", Integer.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final com.jenn.eventsinkorea.domain.user.QUser user;

    public final QUserBuddyId userBuddyId;

    public QBuddyRequest(String variable) {
        this(BuddyRequest.class, forVariable(variable), INITS);
    }

    public QBuddyRequest(Path<? extends BuddyRequest> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBuddyRequest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBuddyRequest(PathMetadata metadata, PathInits inits) {
        this(BuddyRequest.class, metadata, inits);
    }

    public QBuddyRequest(Class<? extends BuddyRequest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.buddy = inits.isInitialized("buddy") ? new QBuddy(forProperty("buddy"), inits.get("buddy")) : null;
        this.user = inits.isInitialized("user") ? new com.jenn.eventsinkorea.domain.user.QUser(forProperty("user")) : null;
        this.userBuddyId = inits.isInitialized("userBuddyId") ? new QUserBuddyId(forProperty("userBuddyId")) : null;
    }

}

