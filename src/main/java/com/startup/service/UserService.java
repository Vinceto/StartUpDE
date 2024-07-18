package com.startup.service;

import com.startup.dao.UserDao;
import com.startup.model.User;

public class UserService {
    private UserDao userDao = new UserDao();

    public int registerUser(User user) {
        return userDao.saveUser(user);
    }

    public boolean loginUser(String email, String password) {
        return userDao.validateUser(email, password);
    }

    public User getUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    public String loginAndGetUserName(String email, String password) {
        User user = userDao.findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user.getName();
        }
        return null;
    }

    public boolean deleteUserById(int userId) {
        return userDao.deleteUser(userId);
    }
}