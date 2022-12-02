package com.naver.cjswovkdnj12.file.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import com.naver.cjswovkdnj12.file.entity.FileEntity;
import com.naver.cjswovkdnj12.file.service.FileService;

@Controller
@RequestMapping("/board")
public class FileController {

	@Autowired
	private FileService fileService;
	
	@GetMapping("/images/{fileId}")
	@ResponseBody
	public Resource downloadImage(@PathVariable("fileId") Long seq, Model model) throws IOException {
		FileEntity file = fileService.getFile(seq);
		return new UrlResource("file:" + file.getSavedPath());
	}

	@GetMapping("/attach/{id}")
	public ResponseEntity<Resource> downloadAttach(@PathVariable Long id) throws MalformedURLException {
		FileEntity file = fileService.getFile(id);
		UrlResource resource = new UrlResource("file:" + file.getSavedPath());
		String encodedFileName = UriUtils.encode(file.getOrgNm(), StandardCharsets.UTF_8);
		String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
	}
}
