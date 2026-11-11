package com.foodie.model;

import java.sql.Timestamp;

public class Order {

	private int orderId;
	private int userId;
	private int restaurantId;
	private Timestamp orderDate;
	private double totalAmount;
	private String status;
	private String paymentMode;
	private String deliveryAddress;

	public Order() {
	}

	public Order(int userId, int restaurantId, double totalAmount, String status, String paymentMode,
			String deliveryAddress) {
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMode = paymentMode;
		this.deliveryAddress = deliveryAddress;
	}

	// ── Getters & Setters ─────────────────────────────────────
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String address) {
		this.deliveryAddress = address;
	}
}