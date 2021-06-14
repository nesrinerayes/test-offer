package com.example.TestOffer.Services;
import com.example.TestOffer.Entity.User;


import com.example.TestOffer.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class UserServiceImpl implements UserService  {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUserById(String id)  {
        LOGGER.debug("Getting user={}", id);
        return userRepository.findByid(id);

    }

    public User getUserByLogin(String login) {
        LOGGER.debug("Getting user={}",login);
        return userRepository.findByLogin(login);


    }

    public Collection<User> getAllUsers() {
        LOGGER.debug("Getting all users");
        return userRepository.findAll();
    }

    public User create(User user) {
        LOGGER.debug("Create User ");
        User u = new User();
        u.setLogin(user.getLogin());
        u.setPassword(user.getPassword());
        u.setFirstName(user.getLastName());
        u.setLastName(user.getLastName());
        u.setAge(user.getAge());
        u.setCountry(user.getCountry());
        // save customers
        return userRepository.save(u);
    }
}
