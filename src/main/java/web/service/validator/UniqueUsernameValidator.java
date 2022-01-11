package web.service.validator;

import org.springframework.beans.factory.annotation.Autowired;
import web.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return username != null && !userService.isUsernameNotUnique(username);
    }

}