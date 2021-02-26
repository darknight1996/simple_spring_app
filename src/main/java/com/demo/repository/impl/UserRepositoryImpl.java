package com.demo.repository.impl;

import com.demo.model.User;
import com.demo.repository.UserRepository;
import com.demo.util.ConnectionHelper;
import com.demo.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// TODO: refactor this class using AOP to reduce a lot of identical code

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final ConnectionHelper connectionHelper;

    private final UserMapper userMapper;

    @Override
    public User getUserById(long id) {
        try {
            Connection connection = connectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM \"User\" WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return userMapper.mapUser(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = connectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM \"User\"");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(userMapper.mapUser(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean addUser(User user) {
        try {
            Connection connection = connectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO \"User\" (name, \"dateOfBirth\") VALUES (?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, new java.sql.Date(user.getDateOfBirth().getTime()));
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            Connection connection = connectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE \"User\" SET \"dateOfBirth\"=?, name=? WHERE id=?");
            preparedStatement.setDate(1, new java.sql.Date(user.getDateOfBirth().getTime()));
            preparedStatement.setString(2, user.getName());
            preparedStatement.setLong(3, user.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(long id) {
        try {
            Connection connection = connectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM \"User\" WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
