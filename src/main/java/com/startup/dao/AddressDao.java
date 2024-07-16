package com.startup.dao;

import com.startup.model.Address;
import com.startup.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDao {
    public boolean saveAddress(Address address) {
        String sql = "INSERT INTO direcciones (nombre, numeracion, usuario_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, address.getName());
            stmt.setString(2, address.getNumber());
            stmt.setInt(3, address.getUserId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Address> findAddressesByUserId(int userId) {
        List<Address> addresses = new ArrayList<>();
        String sql = "SELECT * FROM direcciones WHERE usuario_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Address address = new Address();
                address.setId(rs.getInt("id"));
                address.setName(rs.getString("nombre"));
                address.setNumber(rs.getString("numeracion"));
                address.setUserId(rs.getInt("usuario_id"));
                addresses.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }
}
