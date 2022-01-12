package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.service.RoleService;
import web.service.UserService;

import java.security.Principal;

@Controller
public class MainController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        this.roleService.addDefaultRoles();
        this.userService.addDefaultUsers();
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }


    @GetMapping("/403")
    public String getAccessDenied(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("msg", "Hi " + principal.getName()
                    + "! Sorry, but you do not have permission to access this page!");
        } else {
            model.addAttribute("msg",
                    "Sorry, but You do not have permission to access this page!");
        }
        return "/403";
    }
}
