package com.jenn.eventsinkorea.domain.buddy.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserBuddyId is a Querydsl query type for UserBuddyId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserBuddyId extends BeanPath<UserBuddyId> {

    private static final long serialVersionUID = -2036298009L;

    public static final QUserBuddyId userBuddyId = new QUserBuddyId("userBuddyId");

    public final NumberPath<Long> buddyId = createNumber("buddyId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserBuddyId(String variable) {
        super(UserBuddyId.class, forVariable(variable));
    }

    public QUserBuddyId(Path<? extends UserBuddyId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserBuddyId(PathMetadata metadata) {
        super(UserBuddyId.class, metadata);
    }

}

