package com.example.TestOffer.Controller;

import com.example.TestOffer.Entity.User;
import com.example.TestOffer.Entity.Validation.ValidatorUser;
import com.example.TestOffer.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping(value= "/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ValidatorUser validatorUser;

    /**
     *<p> This method permit to add a multiple validator for user</p>
     * @param binder
     */
    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validatorUser);
    }

    /**
     *<p> This method permit to return all users</p>
     * @return All users
     */

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Collection<User> getAllUsers() {
        return  userService.getAllUsers();
    }

    /**
     *<p> This method permit to return a user according to id</p>
     * @param id
     * @return user by id
     */

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id);

    }

    /**
     *<p> This method permit to return a user according to login</p>
     * @param login
     * @return User by login
     */
    @RequestMapping(value = "/login/{login}", method = RequestMethod.GET)
    public User getUserByLogin(@PathVariable("login") String login) {

        return userService.getUserByLogin(login);

    }

    /**
     *<p> This method permit to create and register a new user</p>
     * @param user
     * @param bindingResult
     * @return user
     * @throws IOException
     */

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody User user, BindingResult bindingResult) throws IOException {

        LOGGER.debug("Processing user create form={}, bindingResult={}", user, bindingResult);

        validatorUser.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            LOGGER.debug(bindingResult.toString());
            return new ResponseEntity("user create not done ", HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            userService.create(user);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate login", e);
            // bindingResult.reject("login.exists", "login already exists");
            return new ResponseEntity("user create not done ", HttpStatus.NOT_ACCEPTABLE);
        }
        // ok
        return new ResponseEntity("user create is done ", HttpStatus.CREATED);

    }

}
