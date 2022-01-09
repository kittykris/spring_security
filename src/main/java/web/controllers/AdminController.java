package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.RoleService;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService service;
    private RoleService roleService;

    @Autowired
    private PasswordEncoder bCryptEncoder;

    @Autowired
    public AdminController(UserService service, RoleService roleService) {
        this.service = service;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String usersList(Model model) {
        model.addAttribute("userList", service.userList());
        return "users";
    }

    @GetMapping("/new_add")
    public String viewNewUser(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleService.allRoles());
        return "addUser";
    }

    @PostMapping("/save")
    public String addNewUser(@Valid @ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("error");
            user.setPassword(bCryptEncoder.encode(user.getPassword()));
            return "addUser";
        }
        service.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("updateUser", service.getUserById(id));
        return "updateUser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid @ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("error");
            return "updateUser";
        }
        service.updateUser(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        service.deleteUser(id);
        return "redirect:/users";
    }

}
