package com.foodie.servlet;

import java.io.IOException;

import com.foodie.daoImple.MenuDaoImpl;
import com.foodie.model.Cart;
import com.foodie.model.CartItem;
import com.foodie.model.MenuItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		Cart cart = (Cart) session.getAttribute("cart");

		String action = req.getParameter("acction");

		try {

			if ("add".equals(action)) {

				int restaurantID = Integer.parseInt(req.getParameter("restaurantID"));

				int menuId = Integer.parseInt(req.getParameter("menuId"));

				int quantity = Integer.parseInt(req.getParameter("quantity"));

				if (restaurantID <= 0 || menuId <= 0 || quantity <= 0) {
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}


				// Check whether the cart belongs to another restaurant.
				if (cart != null
				        && !cart.getItems().isEmpty()
				        && cart.getRestaurantId() != restaurantID) {

				    session.setAttribute(
				        "cartMessage",
				        "Your cart contains items from another restaurant."
				    );

				    resp.sendRedirect(req.getContextPath() + "/Cart.jsp");
				    return;
				}

				// Create a new cart if no cart exists or previous cart is empty.
				if (cart == null || cart.getItems().isEmpty()) {
				    cart = new Cart(restaurantID);
				}

				MenuDaoImpl menuDaoImpl = new MenuDaoImpl();

				MenuItem menuItem = menuDaoImpl.getMenuItemById(menuId);

				if (menuItem == null) {
					resp.sendError(HttpServletResponse.SC_NOT_FOUND);
					return;
				}

				CartItem cartItem = new CartItem(menuId, menuItem.getItemName(), menuItem.getPrice(), quantity);

				cart.addItem(cartItem);

				session.setAttribute("cart", cart);

			} else if ("update".equals(action)) {

				if (cart == null) {
					resp.sendRedirect(req.getContextPath() + "/Cart.jsp");
					return;
				}

				int menuId = Integer.parseInt(req.getParameter("menuId"));

				int quantity = Integer.parseInt(req.getParameter("quantity"));

				if (menuId <= 0 || quantity < 0) {
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}

				cart.updateItem(menuId, quantity);

			} else if ("delete".equals(action)) {

				if (cart == null) {
					resp.sendRedirect(req.getContextPath() + "/Cart.jsp");
					return;
				}

				int menuId = Integer.parseInt(req.getParameter("menuId"));

				if (menuId <= 0) {
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}

				cart.removeItem(menuId);

			} else {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}

			session.setAttribute("cart", cart);

			resp.sendRedirect(req.getContextPath() + "/Cart.jsp");

		} catch (NumberFormatException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		catch (IllegalStateException e) {

		    log("Database error while processing cart.", e);

		    if (!resp.isCommitted()) {
		        resp.sendError(
		                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
		                "Unable to process your cart right now."
		        );
		    }
		}
	}
}
