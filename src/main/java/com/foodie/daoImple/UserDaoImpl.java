
package com.foodie.daoImple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.foodie.dao.UserDao;
import com.foodie.dbconnection.DBConnection;
import com.foodie.model.User;

public class UserDaoImpl implements UserDao {

    private static final String SIGNUP =
        "INSERT INTO users "
        + "(username, password, email, address, createdDate, role) "
        + "VALUES (?, ?, ?, ?, NOW(), 'Customer')";

    private static final String GET_USER_BY_USERNAME =
        "SELECT * FROM users WHERE username = ?";

    private static final String GET_USER_BY_EMAIL =
        "SELECT * FROM users WHERE email = ?";

    private static final String UPDATE_LAST_LOGIN =
        "UPDATE users SET lastLoginDate = NOW() WHERE userId = ?";


    // Signup
    @Override
    public int signup(User user) throws SQLException {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SIGNUP)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getAddress());

            return ps.executeUpdate();
        }
    }


    // Login
    @Override
    public User login(String username, String password)
            throws SQLException {

        User user = getUserByUsername(username);

        if (user == null) {
            return null;
        }

        String hashedPassword = user.getPassword();

        if (hashedPassword == null || hashedPassword.isEmpty()) {
            return null;
        }

        try {
            if (BCrypt.checkpw(password, hashedPassword)) {
                return user;
            }
        } catch (IllegalArgumentException e) {
            // Invalid or corrupted stored BCrypt hash
            return null;
        }

        return null;
    }


    // Get user by username
    @Override
    public User getUserByUsername(String username)
            throws SQLException {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                 con.prepareStatement(GET_USER_BY_USERNAME)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapUser(rs);
                }
            }
        }

        return null;
    }


    // Check username
    @Override
    public boolean usernameExists(String username)
            throws SQLException {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                 con.prepareStatement(GET_USER_BY_USERNAME)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }


    // Check email
    @Override
    public boolean emailExists(String email)
            throws SQLException {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                 con.prepareStatement(GET_USER_BY_EMAIL)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }


    // Update last login
    @Override
    public void updateLastLogin(int userID)
            throws SQLException {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                 con.prepareStatement(UPDATE_LAST_LOGIN)) {

            ps.setInt(1, userID);
            ps.executeUpdate();
        }
    }


    // ResultSet to User
    private User mapUser(ResultSet rs) throws SQLException {

        User user = new User();

        user.setUserId(rs.getInt("userId"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setAddress(rs.getString("address"));
        user.setRole(rs.getString("role"));
        user.setCreatedDate(rs.getTimestamp("createdDate"));
        user.setLastLoginDate(rs.getTimestamp("lastLoginDate"));

        return user;
    }
}
