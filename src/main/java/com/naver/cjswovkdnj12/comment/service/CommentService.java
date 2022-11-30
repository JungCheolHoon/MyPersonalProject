package com.naver.cjswovkdnj12.comment.service;

import java.util.List;

import com.naver.cjswovkdnj12.board.entity.Board;
import com.naver.cjswovkdnj12.comment.entity.Comment2;
import com.naver.cjswovkdnj12.member.entity.Member;

public interface CommentService{

	public void insertComment(Comment2 comment, Board board, Member member); 
	
	public Comment2 getComment(Comment2 comment);
	
	public void updateComment(Comment2 comment);
	
	public void deleteComment(Comment2 comment);
	
	public int countComment();
	
	public List<Comment2> countCommentList(Member member);
}
