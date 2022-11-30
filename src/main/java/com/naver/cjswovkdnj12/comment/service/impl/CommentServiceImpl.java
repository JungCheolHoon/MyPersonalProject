package com.naver.cjswovkdnj12.comment.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.cjswovkdnj12.board.entity.Board;
import com.naver.cjswovkdnj12.board.repository.BoardRepository;
import com.naver.cjswovkdnj12.comment.entity.Comment2;
import com.naver.cjswovkdnj12.comment.repository.CommentRepository;
import com.naver.cjswovkdnj12.comment.service.CommentService;
import com.naver.cjswovkdnj12.member.entity.Member;
import com.naver.cjswovkdnj12.member.repository.MemberRepository;

@Service
public class CommentServiceImpl implements CommentService{

	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	@Transactional
	public void insertComment(Comment2 comment, Board board, Member member) {
		board = boardRepo.findById(board.getSeq()).get();
		member = memberRepo.findById(member.getId()).get();
		board.setMember(member);
		comment.setWriter(member.getId());
		comment.setBoard(board);
		commentRepo.save(comment);
	}
	
	@Override
	public Comment2 getComment(Comment2 comment) {
		
		return commentRepo.findById(comment.getCom_seq()).get();
	}

	@Override
	public void updateComment(Comment2 comment) {
		Comment2 findComment = commentRepo.findById(comment.getCom_seq()).get();
		findComment.setContent(comment.getContent());
		commentRepo.save(findComment);
	}

	@Override
	public void deleteComment(Comment2 comment) {
		commentRepo.deleteComment(String.valueOf(comment.getCom_seq()));
	}
	
	@Override
	public int countComment(){
		List<Comment2> cntComment = (List<Comment2>) commentRepo.findAll();
		return cntComment.size();
	}
	
	@Override
	public List<Comment2> countCommentList(Member member) {
		return commentRepo.countCommentList(member.getId());
	}
	
}
