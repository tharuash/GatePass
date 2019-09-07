package com.b127.gate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.b127.gate.dto.ChangeRole;
import com.b127.gate.dto.RegisterPass;
import com.b127.gate.dto.RegisterUser;
import com.b127.gate.entity.Pass;
import com.b127.gate.entity.Role;
import com.b127.gate.entity.User;
import com.b127.gate.service.PassService;
import com.b127.gate.service.RoleService;
import com.b127.gate.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private PassService passService;

	@Autowired
	private RoleService roleService;

	@RequestMapping("/")
	public String index() {
		return "login";
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
		List<Role> userRoles = userService.findById(userId).getRoles();

		for (Role r : userRoles) {

			if (r.getName().contains("ROLE_ADMIN")) {

				model.addAttribute("passes", passService.findAll());
				return "admin-dashboard";
			}
		}
		model.addAttribute("passes", passService.findAllByUserId(userId));
		return "user-dashboard";
	}

	@RequestMapping("/user/pass")
	public String toAddPass() {
		return "user-pass";
	}

	@RequestMapping(value = "/user/pass", method = RequestMethod.POST)
	public String addPass(@ModelAttribute("pass") RegisterPass registerPass, @SessionAttribute("userId") int userId) {
		if (passService.insert(registerPass, userId) != null) {
			return "redirect:/user/pass?success";
		} else {
			return "redirect:/user/pass?error";
		}
	}

	@RequestMapping("/admin/passes")
	public String toViewPasses(Model model) {
		model.addAttribute("passes", passService.findAll());
		return "admin-passes";
	}

	@RequestMapping("admin/pass/accept/{id}")
	public String acceptPass(@PathVariable("id") String passId) {
		Pass pass = new Pass();
		pass.setId(Integer.valueOf(passId));
		pass.setIsAccepted(true);
		passService.update(pass);

		return "redirect:/admin/passes";
	}

	@RequestMapping("admin/pass/reject/{id}")
	public String rejectPass(@PathVariable("id") String passId) {
		Pass pass = new Pass();
		pass.setId(Integer.valueOf(passId));
		pass.setIsAccepted(false);
		passService.update(pass);

		return "redirect:/admin/passes";
	}

	@RequestMapping(value = "admin/pass/search", method = RequestMethod.POST)
	public String searchPassByUsername(Model model, @RequestBody String username) {

		model.addAttribute("passes", passService.findAllByUser(username.substring(9)));

		return "admin-passes";
	}

	@RequestMapping("/admin/users")
	public String toViewUsers(Model model) {
		model.addAttribute("users", userService.findAll());
		return "admin-users";
	}

	@RequestMapping("/admin/user/{id}")
	public String toViewUserPasses(Model model, @PathVariable("id") String passId) {
		model.addAttribute("passes", passService.findAllByUserId(Integer.valueOf(passId)));
		model.addAttribute("user", "user");
		return "admin-passes";
	}

	@RequestMapping(value = "/admin/user/{id}", method = RequestMethod.POST)
	public String updateUserRoles(Model model, @ModelAttribute("role") ChangeRole changeRole,
			@PathVariable("id") String userId) {
		User user = new User();
		user.setId(Integer.valueOf(userId));
		Role role = new Role();

		if (changeRole.getRolename().contains("admin")) {
			role = roleService.findByRoleName("ROLE_ADMIN");

		} else {
			role = roleService.findByRoleName("ROLE_USER");

		}

		List<Role> list = new ArrayList<Role>();
		list.add(role);

		user.setRoles(list);

		userService.update(user);

		model.addAttribute("users", userService.findAll());
		return "admin-users";
	}

}
