package com.naver.cjswovkdnj12.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -375245876L;

    public static final QMember member = new QMember("member1");

    public final StringPath aboutMe = createString("aboutMe");

    public final StringPath address = createString("address");

    public final ListPath<com.naver.cjswovkdnj12.board.entity.Board, com.naver.cjswovkdnj12.board.entity.QBoard> boardList = this.<com.naver.cjswovkdnj12.board.entity.Board, com.naver.cjswovkdnj12.board.entity.QBoard>createList("boardList", com.naver.cjswovkdnj12.board.entity.Board.class, com.naver.cjswovkdnj12.board.entity.QBoard.class, PathInits.DIRECT2);

    public final StringPath detailAddress = createString("detailAddress");

    public final StringPath email = createString("email");

    public final ComparablePath<Character> enabled = createComparable("enabled", Character.class);

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

