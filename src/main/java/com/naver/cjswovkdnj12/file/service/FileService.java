package com.naver.cjswovkdnj12.file.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.naver.cjswovkdnj12.board.entity.Board;
import com.naver.cjswovkdnj12.board.entity.FileEntity;

public interface FileService {
	public FileEntity saveFile(MultipartFile files, Board board) throws IOException;
	public List<FileEntity> listFile();
	public void deleteFile(Board board);
	public FileEntity getFile(Long id);
}
