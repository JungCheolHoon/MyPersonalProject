package com.naver.cjswovkdnj12.board.entity;

import lombok.Data;

@Data
public class BoardPage {
	
	private int startPage;
	private int endPage;
	private int currentPage;
	private int pageBlock;
	private int totalPage;
	
}
