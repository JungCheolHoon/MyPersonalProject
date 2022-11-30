package com.naver.cjswovkdnj12.member.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.naver.cjswovkdnj12.board.entity.Board;
import com.naver.cjswovkdnj12.comment.entity.Comment2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude="boardList")
public class Member {

	public Member(String id, String password, String name, Role role, char enabled) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.role = role;
		this.enabled = enabled;
	}

	@Id
	@Column(name="MEMBER_ID")
	private String id;
	private String password;
	private String email;
	private String aboutMe;
	private String name;
	private String address;
	private String detailAddress;
	@Enumerated(EnumType.STRING)
	private Role role;
	private char enabled;
	
	@OneToMany(mappedBy="member" , fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Board> boardList = new ArrayList<Board>();

}
