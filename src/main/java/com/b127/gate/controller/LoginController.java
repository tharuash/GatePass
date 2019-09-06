package com.b127.gate.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.b127.gate.dto.RegisterPass;
import com.b127.gate.dto.RegisterUser;
import com.b127.gate.entity.Pass;
import com.b127.gate.entity.Role;
import com.b127.gate.service.PassService;
import com.b127.gate.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private PassService passService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/register")
	public String toRegistrationPage() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegistration(@ModelAttribute("user") RegisterUser registerUser) {

		if (userService.insert(registerUser) != null) {
			return "redirect:/";
		} else {
			return "redirect:/register?failed";
		}

	}

	@RequestMapping("/profile")
	public String toProfilePage(Model model, @SessionAttribute("userId") int userId) {
		Collection<Role> userRoles = userService.findById(userId).getRoles();

		for (Role r : userRoles) {

			if (r.getName().contains("ROLE_ADMIN")) {

				model.addAttribute("passes", passService.findAll());
				return "admin_profile";
			}
		}
		model.addAttribute("passes", passService.findAllByUserId(userId));
		return "user_profile";
	}

	@RequestMapping(value = "/addpass", method = RequestMethod.POST)
	public String addPass(@ModelAttribute("pass") RegisterPass registerPass, @SessionAttribute("userId") int userId) {
		if (passService.insert(registerPass, userId) != null) {
			return "redirect:/profile?success";
		} else {
			return "redirect:/profile?error";
		}
	}
	
	@RequestMapping("accept/{id}")
	public String acceptPass(@PathVariable("id") String passId) {
		Pass pass = new Pass();
		pass.setId(Integer.valueOf(passId));
		pass.setIsAccepted(true);
		passService.update(pass);
		
		return "redirect:/profile";
	}
	
	@RequestMapping("reject/{id}")
	public String rejectPass(@PathVariable("id") String passId) {
		Pass pass = new Pass();
		pass.setId(Integer.valueOf(passId));
		pass.setIsAccepted(false);
		passService.update(pass);
		
		return "redirect:/profile";
	}

}
