package com.example.bootspecialstudy.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "ssar", "1234", "01012345678"));
        users.add(new User(2, "ssar2", "1234", "01012345678"));
        users.add(new User(3, "meteor", "1234", "01012345678"));
        return users;
    }

    public User findById(int id) {
        return new User(id, "ssar", "1234", "01012345678");
    }


}
