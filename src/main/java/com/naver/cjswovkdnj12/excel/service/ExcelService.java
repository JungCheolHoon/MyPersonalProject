package com.naver.cjswovkdnj12.excel.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.naver.cjswovkdnj12.member.entity.Member;

public interface ExcelService{
    
    public void download(HttpServletResponse response, List<Member> memberList) throws IOException;
}
