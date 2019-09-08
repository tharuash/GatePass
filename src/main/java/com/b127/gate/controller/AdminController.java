package com.b127.gate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.b127.gate.dto.ChangeRole;
import com.b127.gate.entity.Pass;
import com.b127.gate.entity.Role;
import com.b127.gate.entity.User;
import com.b127.gate.service.PassService;
import com.b127.gate.service.RoleService;
import com.b127.gate.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private PassService passService;

	@Autowired
	private RoleService roleService;

	@GetMapping("/passes")
	public String toViewPasses(Model model) {
		model.addAttribute("passes", passService.findAll());
		return "admin-passes";
	}

	@GetMapping("/pass/accept/{id}")
	public String acceptPass(@PathVariable("id") String passId) {
		Pass pass = new Pass();
		pass.setId(Integer.valueOf(passId));
		pass.setIsAccepted(true);
		passService.update(pass);

		return "redirect:/admin/passes";
	}

	@GetMapping("/pass/reject/{id}")
	public String rejectPass(@PathVariable("id") String passId) {
		Pass pass = new Pass();
		pass.setId(Integer.valueOf(passId));
		pass.setIsAccepted(false);
		passService.update(pass);

		return "redirect:/admin/passes";
	}

	@PostMapping("/pass/search")
	public String searchPassByUsername(Model model, @RequestBody String username) {

		model.addAttribute("passes", passService.findAllByUser(username.substring(9)));

		return "admin-passes";
	}

	@GetMapping("/users")
	public String toViewUsers(Model model) {
		model.addAttribute("users", userService.findAll());
		return "admin-users";
	}

	@GetMapping("/user/{id}")
	public String toViewUserPasses(Model model, @PathVariable("id") String userId) {
		model.addAttribute("passes", passService.findAllByUserId(Integer.valueOf(userId)));
		model.addAttribute("user", "user");
		model.addAttribute("userId", userId);
		return "admin-passes";
	}

	@PostMapping("/user/{id}")
	public String updateUserRoles(Model model, @ModelAttribute("role") ChangeRole changeRole,
			@PathVariable("id") String userId) {
		User user = new User();
		user.setId(Integer.valueOf(userId));
		Role role = new Role();

		if (changeRole.getRolename().contains("admin")) {
			role = roleService.findByRoleName("ROLE_ADMIN");
			System.out.println("occures............................");

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

	@PostMapping("/user/search/{id}")
	public String searchPassByDate(Model model, @RequestBody String date, @PathVariable("id") String userId) {

		model.addAttribute("passes", passService.findAllByUserAndDate(date.substring(5), Integer.valueOf(userId)));
		model.addAttribute("user", "user");
		model.addAttribute("userId", userId);
		return "admin-passes";
	}
}
