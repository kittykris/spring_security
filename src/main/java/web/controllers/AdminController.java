package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.RoleService;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

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
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleService.allRoles());
        return "addUser";
    }

    @PostMapping("/save")
    public String addNewUser(@ModelAttribute("newUser") @Valid User user,
                             BindingResult result,
                             Model model) {
        if (!userService.isUsernameUnique(user.getUsername())) {
            addErrorIfExistsForField(result, model, "username", "User is already exists");
            return "addUser";
        }
        if (result.hasErrors()) {
            addErrorIfExistsForField(result, model, "roles", "Role must be not empty");
            return "addUser";
        }
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("updateUser", userService.getUserById(id));
        model.addAttribute("allRoles", roleService.allRoles());
        return "updateUser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable("id") long id,
                             @ModelAttribute("updateUser") @Valid User user,
                             BindingResult result,
                             Model model) {
        if (!userService.isUsernameUnique(user.getUsername())) {
            addErrorIfExistsForField(result, model, "username", "User is already exists");
            return "updateUser";
        }
        if (result.hasErrors()) {
            addErrorIfExistsForField(result, model, "roles", "Role must be not empty");
            return "updateUser";
        }
        userService.updateUser(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    private void addErrorIfExistsForField(BindingResult result, Model model, String fieldName, String defaultMessage) {
        result.addError(new FieldError(fieldName, fieldName, defaultMessage));
        model.addAttribute("allRoles", roleService.allRoles());
    }

}
