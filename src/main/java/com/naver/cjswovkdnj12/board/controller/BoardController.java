package com.naver.cjswovkdnj12.board.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import com.naver.cjswovkdnj12.board.entity.Board;
import com.naver.cjswovkdnj12.board.entity.BoardPage;
import com.naver.cjswovkdnj12.board.entity.FileEntity;
import com.naver.cjswovkdnj12.board.entity.Mail;
import com.naver.cjswovkdnj12.board.entity.Search;
import com.naver.cjswovkdnj12.board.service.BoardService;
import com.naver.cjswovkdnj12.file.service.FileService;
import com.naver.cjswovkdnj12.mail.service.MailService;
import com.naver.cjswovkdnj12.member.entity.Member;
import com.naver.cjswovkdnj12.security.config.SecurityUser;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private FileService fileService;

	@Autowired
	private MailService mailService;

	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}

	@RequestMapping("/listBoard")
	public String listBoard(Model model, Search search, String page) {
		if (search.getSearchCondition() == null)
			search.setSearchCondition("TITLE");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");
		int page_ = page != null ? Integer.parseInt(page) - 1 : 0;
		BoardPage boardPage = new BoardPage();
		Page<Board> boardList = boardService.listBoard(search, page_);

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
		return "/listBoard";
	}

	@RequestMapping("/board/categoryBoard")
	public String listBoard(Model model, String category, String page) {
		int page_ = page != null ? Integer.parseInt(page) - 1 : 0;
		BoardPage boardPage = new BoardPage();
		Page<Board> boardList = boardService.listCateBoard(category, page_);
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

		System.out.println("@@endPage" + boardPage.getEndPage());
		model.addAttribute("boardPage", boardPage);

		model.addAttribute("boardList", boardList);
		return "/board/categoryBoard";
	}

	@GetMapping("/board/insertBoard")
	public String insertBoard() {
		return "/board/insertBoard";
	}

	@PostMapping("/board/insertBoardProc")
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
		return "redirect:/listBoard";
	}

	@GetMapping("/board/getBoard")
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

	@GetMapping("/board/images/{fileId}")
	@ResponseBody
	public Resource downloadImage(@PathVariable("fileId") Long seq, Model model) throws IOException {
		FileEntity file = fileService.getFile(seq);
		return new UrlResource("file:" + file.getSavedPath());
	}

	@GetMapping("/board/attach/{id}")
	public ResponseEntity<Resource> downloadAttach(@PathVariable Long id) throws MalformedURLException {
		FileEntity file = fileService.getFile(id);
		UrlResource resource = new UrlResource("file:" + file.getSavedPath());
		String encodedFileName = UriUtils.encode(file.getOrgNm(), StandardCharsets.UTF_8);
		String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
	}

	@GetMapping("/board/updateBoard")
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

	@PostMapping("/board/updateBoardProc")
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

	@GetMapping("/board/deleteBoard")
	public String deleteBoard(Board board, @AuthenticationPrincipal SecurityUser principal) {
		board.setMember(principal.getMember());
		boardService.deleteBoard(board);
		return "redirect:/listBoard";
	}

	@PostMapping("/board/sendMail")
	public String sendMail(Mail mail) {
		mailService.sendMail(mail);
		return "redirect:/listBoard";
	}

}
