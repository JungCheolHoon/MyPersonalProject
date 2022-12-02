package com.naver.cjswovkdnj12.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naver.cjswovkdnj12.member.entity.Member;
import com.naver.cjswovkdnj12.member.entity.Role;
import com.naver.cjswovkdnj12.member.service.MemberService;


@Controller
@RequestMapping("/system")
public class SystemController {
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/join")
	public String memberJoin(Member member) {
		return "/system/join";
	}
	
	@PostMapping("/joinProc")
	public String memberJoinProc(Member member) {	
		
		for(Member member_db:memberService.listMember())	{
			if(member_db.getId().equals(member.getId())) {
				return "/system/joinFail";
			}
		}
		
		member.setRole(Role.ROLE_MEMBER);
		member.setEnabled('y');

		memberService.insertMember(member);	

		return "redirect:/system/login"; 
	}
	
	@GetMapping("/login")
	public String login() {
		return "/system/login";
	}
	
	@GetMapping("/accessDenied")
	public String accessDenied(){
		return "/system/accessDenied";
	}
	
	
}
