package com.naver.cjswovkdnj12.board.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.naver.cjswovkdnj12.board.entity.Board;
import com.naver.cjswovkdnj12.board.entity.Search;
import com.naver.cjswovkdnj12.member.entity.Member;

public interface BoardService {
	public Page<Board> listBoard(Search search, int page, String category);
	public Page<Board> listCateBoard(String category, int page);
	public void insertBoard(Board board);
	public Board getBoard(Board board);
	public void updateBoard(Board board);
	public void deleteBoard(Board board);
	public List<Board> countBoardList(Member member);
}
