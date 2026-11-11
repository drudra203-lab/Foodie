package com.foodie.model;

public class MenuItem {

	private int id;
	private int restaurantId;
	private String itemName;
	private String description;
	private double price;
	private String category; // Starter, Main, Dessert, Drinks
	private boolean isVeg;
	private String imageUrl;

	// ─── Constructors ────────────────────────────────────────────
	public MenuItem() {
	}

	public MenuItem(int id, int restaurantId, String itemName, String description, double price, String category,
			boolean isVeg, String imageUrl) {
		this.id = id;
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.category = category;
		this.isVeg = isVeg;
		this.imageUrl = imageUrl;
	}

	// ─── Getters & Setters ───────────────────────────────────────
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isVeg() {
		return isVeg;
	}

	public void setVeg(boolean isVeg) {
		this.isVeg = isVeg;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", restaurantId=" + restaurantId + ", itemName=" + itemName + ", description="
				+ description + ", price=" + price + ", category=" + category + ", isVeg=" + isVeg + ", imageUrl="
				+ imageUrl + "]";
	}

	
	
}