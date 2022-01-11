package web.service.validator;

import org.springframework.beans.factory.annotation.Autowired;
import web.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private Logger logger = Logger.getLogger(UniqueUsernameValidator.class.getName());

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        logger.log(Level.INFO, "username is valid" + userService.isUsernameUnique(username));
        return userService.isUsernameUnique(username);
    }
}