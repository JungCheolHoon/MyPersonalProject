package com.naver.cjswovkdnj12.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.cjswovkdnj12.board.entity.Board;
import com.naver.cjswovkdnj12.board.entity.FileEntity;
import com.naver.cjswovkdnj12.board.entity.QBoard;
import com.naver.cjswovkdnj12.board.entity.Search;
import com.naver.cjswovkdnj12.board.repository.BoardRepository;
import com.naver.cjswovkdnj12.board.service.BoardService;
import com.naver.cjswovkdnj12.file.repository.FileRepository;
import com.naver.cjswovkdnj12.member.entity.Member;
import com.querydsl.core.BooleanBuilder;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private FileRepository fileRepo;

	@Override
	public Page<Board> listBoard(Search search, int page) {

		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		if (search.getSearchCondition().equals("TITLE")) {
			builder.and(qboard.title.like("%" + search.getSearchKeyword() + "%"));
		} else if (search.getSearchCondition().equals("CONTENT")) {
			builder.and(qboard.content.like("%" + search.getSearchKeyword() + "%"));
		}
		Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "seq");
		return boardRepo.findAll(builder, pageable);
	}

	@Override
	public Page<Board> listCateBoard(String category, int page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "seq");
		return boardRepo.getCateBoardList(category, pageable);
	}

	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}

	@Override
	public Board getBoard(Board board) {
		return boardRepo.findById(board.getSeq()).get();
	}

	@Override
	@Transactional
	public void updateBoard(Board board) {
		for(FileEntity files:board.getFileList()) {
			System.out.println("Q@@fileList"+files.getOrgNm());
		}
		fileRepo.deleteFile(String.valueOf(board.getSeq()));
		boardRepo.save(board);
	}

	@Override
	@Transactional
	@Modifying
	public void deleteBoard(Board board) {
		System.out.println("@@seq" + board.getSeq());
//		boardRepo.deleteBoard(String.valueOf(board.getSeq()));
		boardRepo.delete(board);
	}
	
	@Override
	public List<Board> countBoardList(Member member) {
		return boardRepo.countBoardList(member.getId());
	}

}
