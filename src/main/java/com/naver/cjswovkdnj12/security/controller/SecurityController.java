package com.naver.cjswovkdnj12.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("member")
@Controller
public class SecurityController {


		@GetMapping("/system/login")
		public String login() {
			return "/system/login";
		}
		
		@GetMapping("/system/accessDenied")
		public String accessDenied(){
			return "/system/accessDenied";
		}
	
		@GetMapping("/admin/adminPage")
		public String adminPage(){
			return "/admin/adminPage";
		}
		
}
