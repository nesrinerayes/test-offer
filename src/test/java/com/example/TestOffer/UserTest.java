package com.example.TestOffer;

import com.example.TestOffer.Entity.User;
import com.example.TestOffer.Repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    UserRepository repository;


    User alex, bernard;

    @Before
    public void setUp() {

        repository.deleteAll();

        alex = repository.save(new User("alex@alex.org","alex34","leroy","alex",19,"France"));
        bernard = repository.save(new User("bernard@bernard.com","bernard54","prado","bernard",12,"France"));

    }

    @Test
    public void setsIdOnSave() {
        assertThat(alex.getId()).isNotNull();
    }

    @Test
    public void findsByLogin() {

        User result = repository.findByLogin("alex@alex.org");
        assertThat(result.getLogin().equals(alex.getLogin()));


    }


}
