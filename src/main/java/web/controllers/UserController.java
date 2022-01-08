package web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.models.User;

import java.security.Principal;

@Controller
public class UserController {

	@GetMapping("/user")
	public String userDetails(Model model, Principal principal) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		model.addAttribute("userDetails", loginedUser);
		return "userDetails";
	}

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

	@GetMapping("/")
	public String home() {
		return "redirect:/login";
	}
}