
package com.foodie.dao;

import java.sql.SQLException;
import com.foodie.model.User;

public interface UserDao {

    User login(String username, String password) throws SQLException;

    User getUserByUsername(String username) throws SQLException;

    int signup(User user) throws SQLException;

    boolean usernameExists(String username) throws SQLException;

    boolean emailExists(String email) throws SQLException;

    void updateLastLogin(int userID) throws SQLException;
}
