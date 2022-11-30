package com.naver.cjswovkdnj12.comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComment2 is a Querydsl query type for Comment2
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment2 extends EntityPathBase<Comment2> {

    private static final long serialVersionUID = -458561772L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComment2 comment2 = new QComment2("comment2");

    public final com.naver.cjswovkdnj12.board.entity.QBoard board;

    public final NumberPath<Long> com_seq = createNumber("com_seq", Long.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath writer = createString("writer");

    public QComment2(String variable) {
        this(Comment2.class, forVariable(variable), INITS);
    }

    public QComment2(Path<? extends Comment2> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComment2(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComment2(PathMetadata metadata, PathInits inits) {
        this(Comment2.class, metadata, inits);
    }

    public QComment2(Class<? extends Comment2> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new com.naver.cjswovkdnj12.board.entity.QBoard(forProperty("board"), inits.get("board")) : null;
    }

}

