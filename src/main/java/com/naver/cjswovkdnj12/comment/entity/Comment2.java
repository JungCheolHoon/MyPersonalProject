package com.naver.cjswovkdnj12.comment.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.naver.cjswovkdnj12.board.entity.Board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment2 {
	@Id
	@GeneratedValue
	@Column(name="COMMENT_ID")
	private Long com_seq;

	private String writer;
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date createDate = new Date();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BOARD_ID", nullable = false)
	private Board board;

	public void setBoard(Board board) {
		this.board = board;
		board.getCommentList().add(this);
	}
	
}
