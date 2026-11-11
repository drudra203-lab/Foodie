package com.foodie.daoImple;

import com.foodie.dao.OrderDao;
import com.foodie.dbconnection.DBConnection;
import com.foodie.model.Order;
import com.foodie.model.OrderItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
	// ─── SQL QUERIES ─────────────────────────────────────────
	private static final String SAVE_ORDER = "INSERT INTO `orders` (`userId`,`restaurantId`,`totalAmount`,`status`,`paymentMode`,`address`)"
			+ "values(?,?,?,?,?,?)";
	private static final String SAVE_ORDER_ITEM = "INSERT INTO `orderitems` (`orderId`,`menuId`,`quantity`,`totalPrice`)values(?,?,?,?)";
	private static final String GET_ORDER_BY_USER = "SELECT * FROM `orders` WHERE `userId`=?";

	// ── 1. Save Order — returns generated orderId ─────────────
	// As per lecture: store in orders table FIRST
	// then use the generated orderId for order_items
	@Override
	public int saveOrder(Order order) {
		
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(SAVE_ORDER, Statement.RETURN_GENERATED_KEYS);
		) {
			statement.setInt(1, order.getUserId());
			statement.setInt(2, order.getRestaurantId());
			statement.setDouble(3, order.getTotalAmount());
			statement.setString(4, order.getStatus());
			statement.setString(5, order.getPaymentMode());
			statement.setString(6, order.getDeliveryAddress());
			int rowAffected = statement.executeUpdate();
			
			try (ResultSet keys = statement.getGeneratedKeys();) {    //Get The Auto-Generated OrderId
				if (keys.next()) {
					return keys.getInt("orderId");  //Return The Auto-Generated OrderId
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; //failed 
	}
	
	// ─── 2.Save Order Item ─────────────────────────────────────────
	@Override
	public void saveOrderItem(OrderItem orderItem) {
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(SAVE_ORDER_ITEM);
				) {
			statement.setInt(1, orderItem.getOrderId());
			statement.setInt(2, orderItem.getMenuId());
			statement.setInt(3, orderItem.getQuantity());
			statement.setDouble(4, orderItem.getTotalPrice());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	
	// ─── 3.Get Orders By User ─────────────────────────────────────────
	@Override
	public List<Order> getOrdersByUser(int userId) {
		List<Order> list = new ArrayList<>();
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(GET_ORDER_BY_USER);
				){
			statement.setInt(1, userId);
			try(ResultSet resultSet = statement.executeQuery();){
				while(resultSet.next()) {
					Order order = new Order();
					order.setOrderId(resultSet.getInt("orderId"));
					order.setUserId(resultSet.getInt("userId"));
					order.setRestaurantId(resultSet.getInt("restaurantId"));
					order.setOrderDate(resultSet.getTimestamp("orderDate"));
					order.setTotalAmount(resultSet.getDouble("totalAmount"));
					order.setStatus(resultSet.getString("status"));
					order.setPaymentMode(resultSet.getString("paymentMode"));
					order.setDeliveryAddress(resultSet.getString("deliveryAddress"));
					list.add(order);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}