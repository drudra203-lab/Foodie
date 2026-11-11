
package com.foodie.servlet;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import com.foodie.dao.RestaurantDao;
import com.foodie.daoImple.RestaurantDaoImpl;
import com.foodie.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            RestaurantDao restaurantDao = new RestaurantDaoImpl();

            List<Restaurant> allRestaurants =
                    restaurantDao.getAllRestaurants();

            req.setAttribute("allRestaurants", allRestaurants);

        } catch (IllegalStateException e) {

            getServletContext().log(
                    "Error while loading restaurants.", e);

            req.setAttribute(
                    "restaurantError",
                    "Sorry, we couldn't load restaurants right now. Please try again later."
            );

            req.setAttribute("allRestaurants", new ArrayList<Restaurant>());
        }

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/Restaurant.jsp");

        dispatcher.forward(req, resp);
    }
}
