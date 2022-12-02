package com.naver.cjswovkdnj12.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naver.cjswovkdnj12.email.entity.Mail;
import com.naver.cjswovkdnj12.mail.service.MailService;

@Controller
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private MailService mailService;

	@PostMapping("/sendMail")
	public String sendMail(Mail mail) {
		mailService.sendMail(mail);
		return "redirect:/board/listBoard";
	}

}
