package com.startup.service;
import com.startup.dao.RoleDao;
import com.startup.model.Role;
import java.util.List;

public class RoleService {
    private RoleDao roleDao = new RoleDao();

    public Role getRoleById(int id) {
        return roleDao.findRoleById(id);
    }

    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}