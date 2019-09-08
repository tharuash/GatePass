package com.b127.gate.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.b127.gate.dto.RegisterPass;
import com.b127.gate.service.PassService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private PassService passService;
	
	@GetMapping("/pass")
	public String toAddPass() {
		return "user-pass";
	}

	@PostMapping("/pass")
	public String addPass(@ModelAttribute("pass") RegisterPass registerPass, @SessionAttribute("userId") int userId) throws ParseException {
		if (passService.insert(registerPass, userId) != null) {
			return "redirect:/user/pass?success";
		} else {
			return "redirect:/user/pass?error";
		}
	}
}
