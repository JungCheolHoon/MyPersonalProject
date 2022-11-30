package com.naver.cjswovkdnj12.security.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import com.naver.cjswovkdnj12.member.entity.Member;

public class SecurityUser extends User{
	
	private static final long serialVersionUID = 121452452463L;
	private Member member;
	
	public SecurityUser(Member member) {
		super(member.getId(), member.getPassword(), 
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
		this.member = member;
	}
	
	public Member getMember() {
		return member;
	}
	
}
