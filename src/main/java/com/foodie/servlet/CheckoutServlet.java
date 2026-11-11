package com.foodie.servlet;

import java.io.IOException;

import com.foodie.daoImple.OrderDaoImpl;
import com.foodie.model.Cart;
import com.foodie.model.CartItem;
import com.foodie.model.Order;
import com.foodie.model.OrderItem;
import com.foodie.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		User user = (User) session.getAttribute("user");
		Integer restaurantID = (Integer) session.getAttribute("restaurantID");

		String address = req.getParameter("address");
		String paymentMode = req.getParameter("paymentMode");

		if (user != null) {

			if (cart != null && !cart.getItems().isEmpty()) {
				double totalAmount = cart.getTotalPrice() + 45;
				
				Order order = new Order(user.getUserId(), restaurantID, totalAmount, "pending", paymentMode, address);

				OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
				int orderId = orderDaoImpl.saveOrder(order);
				
				for (CartItem item : cart.getItems().values()) {
					int menuId = item.getId();
					int quantity = item.getQuantity();
					double totalPrice = item.getTotalPrice();
					OrderItem orderItem = new OrderItem(orderId,menuId,quantity,totalPrice);
					orderDaoImpl.saveOrderItem(orderItem);
				}		
			} else {
				resp.sendRedirect("RestaurantServlet");
			}
		} else {
			resp.sendRedirect("Login.jsp");
		}

	}

}
