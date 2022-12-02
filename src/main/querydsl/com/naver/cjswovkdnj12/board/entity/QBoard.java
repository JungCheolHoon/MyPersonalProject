package com.naver.cjswovkdnj12.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -388456770L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final StringPath category = createString("category");

    public final NumberPath<Long> cnt = createNumber("cnt", Long.class);

    public final ListPath<com.naver.cjswovkdnj12.comment.entity.Comment2, com.naver.cjswovkdnj12.comment.entity.QComment2> commentList = this.<com.naver.cjswovkdnj12.comment.entity.Comment2, com.naver.cjswovkdnj12.comment.entity.QComment2>createList("commentList", com.naver.cjswovkdnj12.comment.entity.Comment2.class, com.naver.cjswovkdnj12.comment.entity.QComment2.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final ListPath<com.naver.cjswovkdnj12.file.entity.FileEntity, com.naver.cjswovkdnj12.file.entity.QFileEntity> fileList = this.<com.naver.cjswovkdnj12.file.entity.FileEntity, com.naver.cjswovkdnj12.file.entity.QFileEntity>createList("fileList", com.naver.cjswovkdnj12.file.entity.FileEntity.class, com.naver.cjswovkdnj12.file.entity.QFileEntity.class, PathInits.DIRECT2);

    public final com.naver.cjswovkdnj12.member.entity.QMember member;

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath title = createString("title");

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.naver.cjswovkdnj12.member.entity.QMember(forProperty("member")) : null;
    }

}

