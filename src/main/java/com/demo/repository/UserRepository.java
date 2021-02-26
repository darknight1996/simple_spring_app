package com.demo.repository;

import com.demo.model.User;

import java.util.List;

public interface UserRepository {

    User getUserById(long id);

    List<User> getUsers();

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(long id);

}
