
package com.foodie.servlet;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import com.foodie.dao.MenuDao;
import com.foodie.dao.RestaurantDao;
import com.foodie.daoImple.MenuDaoImpl;
import com.foodie.daoImple.RestaurantDaoImpl;
import com.foodie.model.MenuItem;
import com.foodie.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menuItemServlet")
public class MenuItemServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String restaurantIdParameter = req.getParameter("restaurantID");

		if (restaurantIdParameter == null || restaurantIdParameter.trim().isEmpty()) {

			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Restaurant ID is missing.");
			return;
		}

		int restaurantId;

		try {
			restaurantId = Integer.parseInt(restaurantIdParameter.trim());

			if (restaurantId <= 0) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid restaurant ID.");
				return;
			}

		} catch (NumberFormatException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Restaurant ID must be a valid number.");
			return;
		}

		try {
			RestaurantDao restaurantDao = new RestaurantDaoImpl();

			Restaurant restaurant = restaurantDao.getRestaurantById(restaurantId);

			if (restaurant == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Restaurant not found.");
				return;
			}

			MenuDao menuDao = new MenuDaoImpl();

			List<MenuItem> menuItems = menuDao.getMenuByRestaurantId(restaurantId);

			req.setAttribute("restaurant", restaurant);
			req.setAttribute("MenuItems", menuItems);

		} catch (IllegalStateException e) {

			getServletContext().log("Unable to load menu for restaurant ID: " + restaurantId, e);

			req.setAttribute("menuError", "Sorry, we couldn't load this menu right now. Please try again later.");

			req.setAttribute("menuItems", new ArrayList<MenuItem>());
			req.setAttribute("MenuItems", new ArrayList<MenuItem>());
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/MenuItem.jsp");

		dispatcher.forward(req, resp);
	}
}
