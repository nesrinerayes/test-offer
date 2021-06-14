package com.example.TestOffer.Entity.Validation;

import com.example.TestOffer.Entity.User;
import com.example.TestOffer.Services.UserService;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ValidatorUser implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorUser.class);

    @Autowired
    private UserService userService;



    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        User u = (User) target;
        validateLogin(errors,u);
        validateAge(errors,u);
        validateCountry(errors,u);
    }


    private void validateLogin(Errors errors, User user) {
        for (User u : userService.getAllUsers()) {
            if(u.getLogin().equalsIgnoreCase(user.getLogin())){
                errors.reject("login.exists", "User with this login already exists");
            }
        }
    }

    private void validateAge(Errors errors, User user) {
        if(user.getAge() > 18){
            errors.reject("error age", "only adults ! ");
        }
    }

    private void validateCountry(Errors errors, User user) {
        if(!(user.getCountry().equalsIgnoreCase("France"))){
            errors.reject("error country", "must living in french ! ");
        }
    }
}

