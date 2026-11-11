package com.foodie.dao;

import java.util.List;

import com.foodie.model.MenuItem;

public interface MenuDao {

	// Get all menu items for a specific restaurant
	List<MenuItem> getMenuByRestaurantId(int restaurantId);

	// Get menu items filtered by category for a restaurant
	List<MenuItem> getMenuByCategory(int restaurantId, String category);

	// Get a single menu item by id
	MenuItem getMenuItemById(int id);
}