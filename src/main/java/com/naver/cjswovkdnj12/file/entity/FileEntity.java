package com.naver.cjswovkdnj12.file.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.naver.cjswovkdnj12.board.entity.Board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table
@Entity
@NoArgsConstructor
public class FileEntity {

	@Builder
    public FileEntity(Long id, String orgNm, String savedNm, String savedPath) {
        this.id = id;
        this.orgNm = orgNm;
        this.savedNm = savedNm;
        this.savedPath = savedPath;
    }
	
	@Id
	@GeneratedValue
	@Column(name = "file_id")
	private Long id;
	
	private String orgNm;
	
	private String savedNm;
	
	private String savedPath;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "BOARD_ID")
	private Board board;

	public void setBoard(Board board) {
		this.board = board;
		board.getFileList().add(this);
	}
}
