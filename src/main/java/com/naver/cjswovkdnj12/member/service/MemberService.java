package com.naver.cjswovkdnj12.member.service;

import java.util.List;

import com.naver.cjswovkdnj12.member.entity.Member;

public interface MemberService {

	Member getMember(Member member);

	public void deleteMember(Member member);

	public void insertMember(Member member);

	public void updateMember(Member member);
	
	public List<Member> listMember();
	
}