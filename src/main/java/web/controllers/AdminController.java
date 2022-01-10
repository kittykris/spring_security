package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.RoleService;
import web.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService service;
    private RoleService roleService;

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
        model.addAttribute("allRoles", roleService.allRoles());
        model.addAttribute("newUser", new User());
        return "addUser";
    }

    @PostMapping("/save")
    public String addNewUser(@ModelAttribute User user) {
        service.addUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("allRoles",roleService.allRoles() );
        model.addAttribute("updateUser", service.getUserById(id));
        return "updateUser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable("id") long id, @ModelAttribute User user) {
        service.updateUser(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        service.deleteUser(id);
        return "redirect:/admin/users";
    }

}
