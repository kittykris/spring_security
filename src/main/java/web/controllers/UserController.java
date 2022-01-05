package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import web.models.User;

@Controller
public class UserController {

	@GetMapping("/user")
	public String userDetails(@ModelAttribute("user") User user, Model model) {
		model.addAttribute("userDetails", user);
		model.addAttribute("userDetailsRoles", user.getRoles());
		return "user";
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