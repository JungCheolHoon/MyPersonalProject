package com.naver.cjswovkdnj12.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.naver.cjswovkdnj12.board.entity.Board;
import com.naver.cjswovkdnj12.comment.entity.Comment2;

public interface CommentRepository extends CrudRepository<Comment2, Long>, QuerydslPredicateExecutor<Comment2>{
	@Query("DELETE FROM Comment2 c Where COMMENT_ID = :keyword")
	@Modifying
	@Transactional
	void deleteComment(@Param("keyword") String keyword);	
	
	@Query("SELECT c FROM Comment2 c Where WRITER = :keyword")
	List<Comment2> countCommentList(@Param("keyword") String keyword);
}
