
package com.foodie.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.foodie.daoImple.UserDaoImpl;
import com.foodie.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || password == null
                || username.trim().isEmpty()
                || password.isEmpty()) {

            resp.sendRedirect(req.getContextPath()
                    + "/Login.jsp?error=empty");
            return;
        }

        UserDaoImpl userDao = new UserDaoImpl();

        try {

            User user = userDao.login(username.trim(), password);

            if (user == null) {
                resp.sendRedirect(req.getContextPath()
                        + "/Login.jsp?error=invalid");
                return;
            }

            // Update last login before redirect
            userDao.updateLastLogin(user.getUserId());

            // Remove password hash before storing User in session
            user.setPassword(null);

            // Create or regenerate authenticated session
            HttpSession session = req.getSession(true);
            req.changeSessionId();

            session.setAttribute("user", user);

            resp.sendRedirect(req.getContextPath()
                    + "/RestaurantServlet");

        } catch (SQLException e) {

            log("Database error during login", e);

            resp.sendRedirect(req.getContextPath()
                    + "/Login.jsp?error=server");
        }
    }
}
