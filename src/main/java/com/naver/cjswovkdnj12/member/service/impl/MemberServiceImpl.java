package com.naver.cjswovkdnj12.member.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.naver.cjswovkdnj12.comment.repository.CommentRepository;
import com.naver.cjswovkdnj12.member.entity.Member;
import com.naver.cjswovkdnj12.member.entity.Role;
import com.naver.cjswovkdnj12.member.repository.MemberRepository;
import com.naver.cjswovkdnj12.member.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public Member getMember(Member member) {
		
		// null 인지 아닌지 if문을 쓰지않고 optional 클래스를 사용하여 null인지 확인가능, null이더라도 일단 메소드를 제공해줌
		Optional<Member> findMember = memberRepo.findById(member.getId());
		System.out.println(findMember);
		if(findMember.isPresent())	// findMember 객체가 값을 가지고 있다면 true, findMember != null
			return findMember.get();
		else 
			return null;
	}
	
	@Override
	@Transactional
	public void insertMember(Member member) {
		member.setPassword(encoder.encode(member.getPassword()));
		memberRepo.save(member);
	}

	@Override
	public void updateMember(Member member) {
		Member findMember = memberRepo.findById(member.getId()).get();
		findMember.setAboutMe(member.getAboutMe());
		findMember.setAddress(member.getAddress());
		findMember.setDetailAddress(member.getDetailAddress());
		findMember.setEmail(member.getEmail());
		findMember.setEnabled('y');
		findMember.setName(member.getName());
		findMember.setRole(Role.ROLE_MEMBER);
		memberRepo.save(findMember);
	}

	@Override
	public void deleteMember(Member member) {
		memberRepo.deleteById(member.getId());
	}
	
	@Override
	public List<Member> listMember(){
		List<Member> memberList = (List<Member>) memberRepo.findAll();
		return memberList;
	}
	
}
