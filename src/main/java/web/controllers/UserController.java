package web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.models.User;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping
	public String userDetails(Model model, Principal principal) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		model.addAttribute("userDetails", loginedUser);
		return "userDetails";
	}
}