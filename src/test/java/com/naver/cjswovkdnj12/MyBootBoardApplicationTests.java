package com.naver.cjswovkdnj12;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.naver.cjswovkdnj12.board.entity.Board;
import com.naver.cjswovkdnj12.board.repository.BoardRepository;
import com.naver.cjswovkdnj12.member.entity.Member;
import com.naver.cjswovkdnj12.member.entity.Role;
import com.naver.cjswovkdnj12.member.repository.MemberRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
class MyBootBoardApplicationTests {

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	void asd() {
		Member member1 = new Member();
		member1.setId("testid");
		member1.setPassword(encoder.encode("testpw"));
		member1.setName("둘리");
		member1.setAddress("부산광역시 해운대구");
		member1.setAboutMe("나는 둘리입니다");
		member1.setDetailAddress("센텀벤처타운");
		member1.setEmail("killa@naver.com");
		member1.setRole(Role.ROLE_MEMBER);
		member1.setEnabled('y');
		memberRepo.save(member1);
		for(int i = 0; i <132; i++) {
			Board board = new Board();
			board.setMember(member1);
			board.setCategory("공지");
			board.setContent("야호"+i);
			board.setTitle("test게시글"+i);
			boardRepo.save(board);
		}
	}
	
//	@Test
//	void contextLoads() {
//		Member member1 = new Member("testid", "testpw", "둘리", Role.ROLE_MEMBER,'y');
//		memberRepo.save(member1);
//		Member member2 = new Member("adminid", "adminpw","도우너", Role.ROLE_ADMIN,'y');
//		memberRepo.save(member2);
//	
//		
//		for(int i=1; i<=3; i++) {
//			Board board = new Board();
//			board.setMember(member1);
//			board.setTitle("둘리가 등록한 게시글 "+i);
//			board.setContent("둘리가 등록한 게시글 내용 "+i);
//			boardRepo.save(board);
//		}
//		for(int i=1; i<=3; i++) {
//			Board board = new Board();
//			board.setMember(member2);
//			board.setTitle("도우너가 등록한 게시글 "+i);
//			board.setContent("도우너가 등록한 게시글 내용 "+i);
//			boardRepo.save(board);
//		}
//		
//	}
	
	@Autowired
	private PasswordEncoder encoder;
//	
//	@Test
//	public void contextLoads() {
//		Member member1 = new Member();
//		member1.setId("testid");
//		member1.setPassword(encoder.encode("testpw"));
//		member1.setName("둘리");
//		member1.setRole(Role.ROLE_MEMBER);
//		member1.setEnabled('y');
//		memberRepo.save(member1);
//		
//		Member member = new Member();
//		member.setId("adminid");
//		member.setPassword(encoder.encode("adminpw"));
//		member.setName("도우너");
//		member.setRole(Role.ROLE_ADMIN);
//		member.setEnabled('y');
//		memberRepo.save(member);
//	}

}
