package com.naver.cjswovkdnj12.excel.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.naver.cjswovkdnj12.excel.service.ExcelService;
import com.naver.cjswovkdnj12.member.entity.Member;

@Service
public class ExcelServiceImpl implements ExcelService{
    
	@Override
    public void download(HttpServletResponse response, List<Member> memberList) throws IOException {
        Workbook wb = new XSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("MemberListExcel");
        int rowNum = 0;
        Cell cell = null;
        Row row = null;
 
        // Header
        int cellNum = 0;
        row = sheet.createRow(rowNum++);
        cell = row.createCell(cellNum++);
        cell.setCellValue("아이디");
        cell = row.createCell(cellNum++);
        cell.setCellValue("이름");
        cell = row.createCell(cellNum++);
        cell.setCellValue("이메일");
        cell = row.createCell(cellNum++);
        cell.setCellValue("주소");
        cell = row.createCell(cellNum++);
        cell.setCellValue("상세주소");
 
        int num = 1;
        // Body
        for (Member member: memberList) {
            cellNum = 0;
            row = sheet.createRow(rowNum++);
            cell = row.createCell(cellNum++);
            cell.setCellValue(num);
            cell = row.createCell(cellNum++);
            cell.setCellValue(member.getName());
            cell = row.createCell(cellNum++);
            cell.setCellValue(member.getEmail());
            cell = row.createCell(cellNum++);
            cell.setCellValue(member.getAddress());
            cell = row.createCell(cellNum++);
            cell.setCellValue(member.getDetailAddress());
            num++;
        }
 
        // Download
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=MemberInfo.xlsx");
        try {
            wb.write(response.getOutputStream());
        } finally {
            wb.close();
        }
    }
}
