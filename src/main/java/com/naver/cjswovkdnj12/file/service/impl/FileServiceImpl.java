package com.naver.cjswovkdnj12.file.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.naver.cjswovkdnj12.board.entity.Board;
import com.naver.cjswovkdnj12.board.entity.FileEntity;
import com.naver.cjswovkdnj12.file.repository.FileRepository;
import com.naver.cjswovkdnj12.file.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Value("${file.dir}")
	private String fileDir;

	@Autowired
	private FileRepository fileRepository;

	@Override
	public FileEntity saveFile(MultipartFile files, Board board) throws IOException {

		// 원래 파일 이름 추출
		String origName = files.getOriginalFilename();

		// 파일 이름으로 쓸 uuid 생성
		String uuid = UUID.randomUUID().toString();

		// 확장자 추출(ex : .png)
		String extension = origName.substring(origName.lastIndexOf("."));

		// uuid와 확장자 결합
		String savedName = uuid + extension;

		// 파일을 불러올 때 사용할 파일 경로
		String savedPath = fileDir + savedName;

		// 파일 엔티티 생성
		FileEntity file = FileEntity.builder().orgNm(origName).savedNm(savedName).savedPath(savedPath).build();

		// 실제로 로컬에 uuid를 파일명으로 저장
		files.transferTo(new File(savedPath));

		return file;
	}

	@Override
	public List<FileEntity> listFile() {
		return (List<FileEntity>) fileRepository.findAll();
	}

	@Override
	public void deleteFile(Board board) {
		fileRepository.deleteFile(String.valueOf(board.getSeq()));
	}

	@Override
	public FileEntity getFile(Long id) {
		return fileRepository.findById(id).orElse(null);
	}
}
