package com.naver.cjswovkdnj12.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.naver.cjswovkdnj12.board.entity.Board;
import com.naver.cjswovkdnj12.board.service.BoardService;
import com.naver.cjswovkdnj12.comment.service.CommentService;
import com.naver.cjswovkdnj12.member.entity.Member;
import com.naver.cjswovkdnj12.member.entity.Role;
import com.naver.cjswovkdnj12.member.service.MemberService;
import com.naver.cjswovkdnj12.security.config.SecurityUser;


@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/member/userInfo")
	public String memberInfo(Model model, @AuthenticationPrincipal SecurityUser principal) {
		int cntBoard = boardService.countBoardList(principal.getMember()).size();
		int cntComment = commentService.countCommentList(principal.getMember()).size();
		model.addAttribute("cntBoard",cntBoard);
		model.addAttribute("cntComment",cntComment);
		
		return "/member/userInfo";
	}

	@GetMapping("/system/join")
	public String memberJoin(Member member) {
		return "/system/join";
	}
	
	@PostMapping("/system/joinProc")
	public String memberJoinProc(Member member) {	
		
		for(Member member_db:memberService.listMember())	{
			if(member_db.getId().equals(member.getId())) {
				return "/system/join";
			}
		}
		
		member.setRole(Role.ROLE_MEMBER);
		member.setEnabled('y');

		memberService.insertMember(member);	

		return "redirect:/system/login"; 
	}
	
	@PostMapping("/member/memberUpdate")
	public String memberUpdate(Member member, @AuthenticationPrincipal SecurityUser principal) {
		System.out.println("updatemember@@"+member.toString());
		member.setRole(Role.ROLE_MEMBER);
		member.setEnabled('y');
		memberService.updateMember(member);
		return "redirect:/logout";
	}
	
}
