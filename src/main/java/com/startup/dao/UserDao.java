package com.startup.dao;
import com.startup.model.User;
import com.startup.util.DatabaseUtil;
import java.sql.*;

public class UserDao {
    public boolean saveUser(User user) {
        String sql = "INSERT INTO usuarios (correo, nick, nombre, password, peso, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getNick());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getWeight());
            stmt.setDate(6, new java.sql.Date(user.getCreatedAt().getTime()));
            stmt.setDate(7, new java.sql.Date(user.getUpdatedAt().getTime()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User findUserByEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE correo = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("correo"));
                user.setNick(rs.getString("nick"));
                user.setName(rs.getString("nombre"));
                user.setPassword(rs.getString("password"));
                user.setWeight(rs.getInt("peso"));
                user.setCreatedAt(rs.getDate("created_at"));
                user.setUpdatedAt(rs.getDate("updated_at"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean validateUser(String email, String password) {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND password = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}