package com.example.TestOffer.Services;

import com.example.TestOffer.Entity.User;

import java.util.Collection;

public interface UserService {

    public User getUserById(String id);

    public User getUserByLogin(String login);

    public Collection<User> getAllUsers();

    public User create(User u);

}
