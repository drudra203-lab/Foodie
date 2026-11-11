
package com.foodie.servlet;

import java.io.IOException;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.foodie.daoImple.UserDaoImpl;
import com.foodie.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String address = req.getParameter("address");

        if (username == null || email == null
                || password == null || address == null
                || username.trim().isEmpty()
                || email.trim().isEmpty()
                || password.isEmpty()
                || address.trim().isEmpty()) {

            resp.sendRedirect(req.getContextPath()
                    + "/Signup.jsp?error=empty");
            return;
        }

        username = username.trim();
        email = email.trim();
        address = address.trim();

        // Basic password length validation
        if (password.length() < 8
                || password.getBytes(
                    java.nio.charset.StandardCharsets.UTF_8).length > 72) {

            resp.sendRedirect(req.getContextPath()
                    + "/Signup.jsp?error=password");
            return;
        }

        UserDaoImpl userDao = new UserDaoImpl();

        try {

            if (userDao.usernameExists(username)) {
                resp.sendRedirect(req.getContextPath()
                        + "/Signup.jsp?error=username");
                return;
            }

            if (userDao.emailExists(email)) {
                resp.sendRedirect(req.getContextPath()
                        + "/Signup.jsp?error=email");
                return;
            }

            String hashedPassword =
                    BCrypt.hashpw(password, BCrypt.gensalt(12));

            User user = new User(
                    username,
                    hashedPassword,
                    email,
                    address
            );

            int rowsAffected = userDao.signup(user);

            if (rowsAffected == 1) {

                resp.sendRedirect(req.getContextPath()
                        + "/Login.jsp?signup=success");

            } else {

                resp.sendRedirect(req.getContextPath()
                        + "/Signup.jsp?error=failed");
            }

        } catch (SQLException e) {

            log("Database error during signup", e);

            // MySQL duplicate key error
            if ("23000".equals(e.getSQLState())) {
                resp.sendRedirect(req.getContextPath()
                        + "/Signup.jsp?error=duplicate");
            } else {
                resp.sendRedirect(req.getContextPath()
                        + "/Signup.jsp?error=server");
            }
        }
    }
}
