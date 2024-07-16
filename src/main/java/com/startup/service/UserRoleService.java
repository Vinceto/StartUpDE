package com.startup.service;
import com.startup.dao.UserRoleDao;
import com.startup.model.UserRole;

public class UserRoleService {
    private UserRoleDao userRoleDao = new UserRoleDao();

    public boolean addUserRole(UserRole userRole) {
        return userRoleDao.saveUserRole(userRole);
    }
}