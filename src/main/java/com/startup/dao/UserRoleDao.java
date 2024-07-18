package com.startup.dao;

import com.startup.model.UserRole;
import com.startup.util.DatabaseUtil;

import java.sql.*;

public class UserRoleDao {
    public boolean saveUserRole(UserRole userRole) {
        String sql = "INSERT INTO roles_usuarios (usuario_id, rol_id) VALUES (?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userRole.getUserId());
            stmt.setInt(2, userRole.getRoleId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String obtenerRoleUsuario(int id) {
        String roleName = null;
        String sql = "SELECT r.nombre FROM roles r INNER JOIN roles_usuarios ru ON ru.rol_id = r.id WHERE ru.usuario_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    roleName = rs.getString("nombre");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roleName;
    }
}