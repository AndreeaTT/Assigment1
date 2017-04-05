package com.spr.validation;

import com.spr.service.UserService;
import com.spr.model.User;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.Validation;

/**
 * Created by Andreea ADM on 3/26/2017.
 */

@Component
public class LoginValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {

        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmpty(errors, "username", "user.username.empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "user.password.empty");

        if (user.getUsername() != null)
            if (user.getUsername().length() < 8 || user.getUsername().length() > 35)
                errors.rejectValue("username","user.username.length");

        if (user.getUsername() != null)
            if (user.getPassword().length() < 8 || user.getPassword().length() > 35)
                errors.rejectValue("password","user.password.length");

        if (user.getUsername() != null)
            if (!(user.getUsername().matches("[a-zA-Z](.)*")))
                errors.rejectValue("username", "user.username.invalid");

        if (user.getPassword() != null)
            if (!(user.getPassword().matches("[a-zA-Z](.)*")))
                errors.rejectValue("password", "user.password.invalid");

    }
}
