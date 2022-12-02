package com.naver.cjswovkdnj12.excel.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.naver.cjswovkdnj12.member.entity.Member;

public interface ExcelService{
    
    public void download(HttpServletResponse response, List<Member> memberList) throws IOException;
}
