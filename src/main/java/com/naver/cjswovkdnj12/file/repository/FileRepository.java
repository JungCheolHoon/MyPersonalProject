package com.naver.cjswovkdnj12.file.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.naver.cjswovkdnj12.board.entity.FileEntity;

public interface FileRepository extends CrudRepository<FileEntity, Long>, QuerydslPredicateExecutor<FileEntity>{
	@Query("DELETE FROM FileEntity F Where BOARD_ID = :keyword")
	@Modifying
	@Transactional
	void deleteFile(@Param("keyword") String keyword);
}
