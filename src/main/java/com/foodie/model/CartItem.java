package com.foodie.model;

public class CartItem {

	private int id;
	private String itemName;
	private double price;
	private int quantity;

	public CartItem() {
	}

	public CartItem(int id, String itemName, double price, int quantity) {
		this.id = id;
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getItemName() {
		return itemName;
	}

	public void setItemName(String name) {
		this.itemName = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int qty) {
		this.quantity = qty;
	}

	public double getTotalPrice() {
		return price * quantity;
	}
}
