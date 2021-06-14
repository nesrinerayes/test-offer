package com.example.TestOffer.Repository;

import com.example.TestOffer.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByLogin(String login);
    public User findByid(String id);
}
