package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import web.models.User;

@Controller
public class UserController {

	@GetMapping("/user")
	public String userDetails(@ModelAttribute("user") User user, Model model) {
		model.addAttribute("userDetails", user);
		return "user";
	}

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

}