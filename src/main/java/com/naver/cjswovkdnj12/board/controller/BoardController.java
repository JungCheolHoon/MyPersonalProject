package com.naver.cjswovkdnj12.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.naver.cjswovkdnj12.board.entity.Board;
import com.naver.cjswovkdnj12.board.entity.BoardPage;
import com.naver.cjswovkdnj12.board.entity.Search;
import com.naver.cjswovkdnj12.board.service.BoardService;
import com.naver.cjswovkdnj12.email.entity.Mail;
import com.naver.cjswovkdnj12.file.entity.FileEntity;
import com.naver.cjswovkdnj12.file.service.FileService;
import com.naver.cjswovkdnj12.mail.service.MailService;
import com.naver.cjswovkdnj12.security.config.SecurityUser;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private FileService fileService;

	@Autowired
	private MailService mailService;

	@RequestMapping("/listBoard")
	public String listBoard(Model model, Search search, String page, String category) {
		if (search.getSearchCondition() == null)
			search.setSearchCondition("TITLE");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");
		int page_ = page != null ? Integer.parseInt(page) - 1 : 0;
		BoardPage boardPage = new BoardPage();
		Page<Board> boardList = boardService.listBoard(search, page_,category);

		try {
			page_ = page_ + 1;

			// 현재 페이지
			boardPage.setCurrentPage(page_);
			// 한 블록당 보여줄 페이지 수
			boardPage.setPageBlock(boardList.getSize());
			// 전체 게시글 수
			boardPage.setTotalPage(boardList.getTotalPages());
			// 블록 당 첫 페이지 번호
			boardPage.setStartPage(((page_ - 1) / boardList.getSize()) * boardList.getSize() + 1);
			// 블록 당 마지막 페이지 번호
			boardPage.setEndPage(boardPage.getStartPage() + boardList.getSize() - 1);
			if (boardPage.getEndPage() > boardPage.getTotalPage()) {
				boardPage.setEndPage(boardPage.getTotalPage());
			}
		} catch (Exception e) {

		}

		model.addAttribute("boardPage", boardPage);
		model.addAttribute("boardList", boardList);
		return "/board/listBoard";
	}

	@GetMapping("/insertBoard")
	public String insertBoard() {
		return "/board/insertBoard";
	}

	@PostMapping("/insertBoardProc")
	public String insertBoardProc(Board board, @AuthenticationPrincipal SecurityUser principal,
			@RequestParam("files") List<MultipartFile> files) throws IOException {
		board.setMember(principal.getMember());
		System.out.println("@@filesboolean" + files.isEmpty());

		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				fileService.saveFile(file, board).setBoard(board);
			}
		}

		boardService.insertBoard(board);
		return "redirect:/board/listBoard";
	}

	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model, @AuthenticationPrincipal SecurityUser principal) {
		Board findBoard = boardService.getBoard(board);
		if (findBoard.getCnt() == null) {
			findBoard.setCnt(0L);
		} else {
			findBoard.setCnt(findBoard.getCnt() + 1);
		}
		boardService.insertBoard(findBoard);

		List<FileEntity> imgFileList = new ArrayList<FileEntity>();
		List<FileEntity> attachFileList = new ArrayList<FileEntity>();

		for (FileEntity file : findBoard.getFileList()) {
			if (file.getSavedPath().substring(file.getSavedPath().length() - 3, file.getSavedPath().length())
					.equals("jpg")
					|| file.getSavedPath().substring(file.getSavedPath().length() - 3, file.getSavedPath().length())
							.equals("png")) {
				imgFileList.add(file);
			} else {
				attachFileList.add(file);
			}
		}

		model.addAttribute("attachFileList", attachFileList);
		model.addAttribute("imgFileList", imgFileList);
		model.addAttribute("board", findBoard);
		model.addAttribute("member", principal.getMember());
		return "/board/getBoard";
	}

	@GetMapping("/updateBoard")
	public String updateBoard(Board board, Model model) {
		Board findBoard = boardService.getBoard(board);

		List<FileEntity> imgFileList = new ArrayList<FileEntity>();
		List<FileEntity> attachFileList = new ArrayList<FileEntity>();

		for (FileEntity file : findBoard.getFileList()) {
			if (file.getSavedPath().substring(file.getSavedPath().length() - 3, file.getSavedPath().length())
					.equals("jpg")
					|| file.getSavedPath().substring(file.getSavedPath().length() - 3, file.getSavedPath().length())
							.equals("png")) {
				imgFileList.add(file);
			} else {
				attachFileList.add(file);
			}
		}

		model.addAttribute("attachFileList", attachFileList);
		model.addAttribute("imgFileList", imgFileList);
		model.addAttribute("board", findBoard);
		return "/board/updateBoard";
	}

	@PostMapping("/updateBoardProc")
	public String updateBoardProc(Board board, @AuthenticationPrincipal SecurityUser principal,
			@RequestParam("files") List<MultipartFile> files) throws IOException {
		board.setMember(principal.getMember());

		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				fileService.saveFile(file, board).setBoard(board);
			}
		}

		boardService.updateBoard(board);
		return "redirect:/board/getBoard?seq=" + board.getSeq();
	}

	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board, @AuthenticationPrincipal SecurityUser principal) {
		board.setMember(principal.getMember());
		boardService.deleteBoard(board);
		return "redirect:/board/listBoard";
	}

	@PostMapping("/sendMail")
	public String sendMail(Mail mail) {
		mailService.sendMail(mail);
		return "redirect:/board/listBoard";
	}

}
