package com.naver.cjswovkdnj12.mail.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.naver.cjswovkdnj12.email.entity.Mail;
import com.naver.cjswovkdnj12.mail.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendMail(Mail mail) {
		List<String> toUserList = new ArrayList<String>();
		
		toUserList.add(mail.getReceiver());
		
		int toUserSize = toUserList.size();
		
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		
		simpleMessage.setTo((String[]) toUserList.toArray(new String[toUserSize]));
		
		simpleMessage.setSubject(mail.getMailTitle());
		simpleMessage.setText(mail.getMailContent());
		
		javaMailSender.send(simpleMessage);
		
	}	
}
