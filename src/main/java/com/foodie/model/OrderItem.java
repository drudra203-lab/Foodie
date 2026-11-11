package com.foodie.model;

public class OrderItem {

	private int orderItemId;
	private int orderId;
	private int menuId;
	private int quantity;
	private double totalPrice;

	public OrderItem() {
	}

	public OrderItem(int orderId, int menuId, int quantity, double totalPrice) {
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	// ── Getters & Setters ─────────────────────────────────────
	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
