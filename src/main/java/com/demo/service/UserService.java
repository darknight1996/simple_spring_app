package com.demo.service;

import com.demo.model.User;

import java.util.List;

public interface UserService {

    User getUserById(long id);

    List<User> getUsers();

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(long id);

}
