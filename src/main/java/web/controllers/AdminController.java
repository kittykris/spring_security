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

    private UserService userService;
    private RoleService roleService;
    private final String mainRedirect = "redirect:/admin/users";

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String usersList(Model model) {
        model.addAttribute("userList", userService.userList());
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
        userService.addUser(user);
        return mainRedirect;
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("allRoles", roleService.allRoles());
        model.addAttribute("updateUser", userService.getUserById(id));
        return "updateUser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable("id") long id,
                             @ModelAttribute User user) {
        userService.updateUser(id, user);
        return mainRedirect;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return mainRedirect;
    }

}
