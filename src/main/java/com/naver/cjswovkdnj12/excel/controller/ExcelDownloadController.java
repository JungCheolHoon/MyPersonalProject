package com.naver.cjswovkdnj12.excel.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naver.cjswovkdnj12.excel.service.ExcelService;
import com.naver.cjswovkdnj12.member.service.MemberService;

@Controller
@RequestMapping("/")
public class ExcelDownloadController {
 
	@Autowired
    private ExcelService excelService;
	
	@Autowired
	private MemberService memberService;
 
    @GetMapping("/admin/download")
    public void download(HttpServletResponse response) throws IOException {
        excelService.download(response,memberService.listMember());
    }
}