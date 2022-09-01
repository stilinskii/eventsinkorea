package com.jenn.eventsinkorea.domain.buddy.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBuddyReview is a Querydsl query type for BuddyReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBuddyReview extends EntityPathBase<BuddyReview> {

    private static final long serialVersionUID = 1098413167L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBuddyReview buddyReview = new QBuddyReview("buddyReview");

    public final QBuddy buddy;

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> rating = createNumber("rating", Double.class);

    public final com.jenn.eventsinkorea.domain.user.QUser user;

    public QBuddyReview(String variable) {
        this(BuddyReview.class, forVariable(variable), INITS);
    }

    public QBuddyReview(Path<? extends BuddyReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBuddyReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBuddyReview(PathMetadata metadata, PathInits inits) {
        this(BuddyReview.class, metadata, inits);
    }

    public QBuddyReview(Class<? extends BuddyReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.buddy = inits.isInitialized("buddy") ? new QBuddy(forProperty("buddy"), inits.get("buddy")) : null;
        this.user = inits.isInitialized("user") ? new com.jenn.eventsinkorea.domain.user.QUser(forProperty("user")) : null;
    }

}

