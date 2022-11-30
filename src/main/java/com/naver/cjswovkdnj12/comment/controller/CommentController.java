package com.naver.cjswovkdnj12.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.naver.cjswovkdnj12.board.entity.Board;
import com.naver.cjswovkdnj12.board.service.BoardService;
import com.naver.cjswovkdnj12.comment.entity.Comment2;
import com.naver.cjswovkdnj12.comment.service.CommentService;
import com.naver.cjswovkdnj12.security.config.SecurityUser;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/comment/insertComment")
	public String insertComment(Board board, Comment2 comment, @AuthenticationPrincipal SecurityUser principal) {
		commentService.insertComment(comment, board, principal.getMember());
		return "redirect:/board/getBoard?seq=" +board.getSeq();
	}
	
	@GetMapping("/comment/deleteComment")
	public String deleteComment(Comment2 comment, Board board) {
		commentService.deleteComment(comment);
		System.out.println("@@com"+comment.getCom_seq());
		System.out.println("@@boseq"+board.getSeq());
		
		return "redirect:/board/getBoard?seq="+board.getSeq(); 
	}
	
}
