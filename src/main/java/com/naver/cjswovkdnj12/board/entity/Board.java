package com.naver.cjswovkdnj12.board.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.naver.cjswovkdnj12.comment.entity.Comment2;
import com.naver.cjswovkdnj12.file.entity.FileEntity;
import com.naver.cjswovkdnj12.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = "member")
public class Board {

	@Id
	@GeneratedValue
	@Column(name = "BOARD_ID")
	private Long seq;
	private String title;

	private String content;
	private String category;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@Column(insertable = false, columnDefinition = "number default 0")
	private Long cnt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID", nullable = false)
	private Member member;

	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Comment2> commentList = new ArrayList<Comment2>();

	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
	private List<FileEntity> fileList = new ArrayList<FileEntity>();

	public void setMember(Member member) {
		this.member = member;
		member.getBoardList().add(this);
	}

}