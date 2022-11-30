package com.naver.cjswovkdnj12.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.naver.cjswovkdnj12.board.entity.Board;

public interface BoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board>{
	
	@Query("SELECT b FROM Board b")
	Page<Board> getBoardList(Pageable pageable);
	
	@Query("SELECT b FROM Board b Where CATEGORY = :keyword")
	Page<Board> getCateBoardList(@Param("keyword") String keyword , Pageable pageable);
	
	@Query("DELETE FROM Board b Where BOARD_ID = :keyword")
	@Modifying
	@Transactional
	void deleteBoard(@Param("keyword") String keyword);
	
	@Query("SELECT b FROM Board b Where MEMBER_ID = :keyword")
	List<Board> countBoardList(@Param("keyword") String keyword);
}
