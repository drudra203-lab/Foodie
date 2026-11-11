package com.foodie.dao;

import java.util.List;

import com.foodie.model.Restaurant;

public interface RestaurantDao {

	// Get all restaurants
	List<Restaurant> getAllRestaurants();

	// Get restaurants filtered by category (e.g. "Desserts", "Asian")
	List<Restaurant> getRestaurantsByCategory(String category);

	// Get restaurants filtered by location
	List<Restaurant> getRestaurantsByLocation(String location);

	// Get a single restaurant by its ID
	Restaurant getRestaurantById(int id);
}