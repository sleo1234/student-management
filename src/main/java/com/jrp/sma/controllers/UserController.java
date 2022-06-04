package com.jrp.sma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jrp.sma.dao.RoleRepository;
import com.jrp.sma.entities.Role;
import com.jrp.sma.entities.User;
import com.jrp.sma.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired RoleRepository roleRepo;
	
	@Autowired UserService userService;
	

	@GetMapping("/new")
	public String newUser (Model model) {
		User user = new User();
		user.setEnabled(true);
		
		List<Role> listRoles = (List<Role>)roleRepo.findAll();
		
		model.addAttribute("user",user);
		model.addAttribute("listRoles",listRoles);
		model.addAttribute("pageTitle", "New user");
		
		return "users/user_form";
	}
	
	@PostMapping("/save")
	
	public String saveUser (User user, RedirectAttributes ra) {
		userService.save(user);
		ra.addFlashAttribute("message", "User has been set succesfully");
		return "redirect:/";
	}
	

	
}
