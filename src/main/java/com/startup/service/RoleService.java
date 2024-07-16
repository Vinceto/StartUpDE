package com.startup.service;

import com.startup.dao.RoleDao;
import com.startup.model.Role;

public class RoleService {
    private RoleDao roleDao = new RoleDao();

    public Role getRoleById(int id) {
        return roleDao.findRoleById(id);
    }
}