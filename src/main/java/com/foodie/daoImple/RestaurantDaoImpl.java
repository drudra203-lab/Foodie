
package com.foodie.daoImple;

import com.foodie.dao.RestaurantDao;
import com.foodie.dbconnection.DBConnection;
import com.foodie.model.Restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDaoImpl implements RestaurantDao {

    private static final String GET_ALL_RESTAURANTS =
            "SELECT id, name, category, location, rating, priceForTwo, " +
            "deliveryTime, offer, imageUrl FROM restaurant ORDER BY id ASC";

    private static final String GET_RESTAURANTS_BY_CATEGORY =
            "SELECT id, name, category, location, rating, priceForTwo, " +
            "deliveryTime, offer, imageUrl FROM restaurant WHERE category = ? ORDER BY id ASC";

    private static final String GET_RESTAURANTS_BY_LOCATION =
            "SELECT id, name, category, location, rating, priceForTwo, " +
            "deliveryTime, offer, imageUrl FROM restaurant WHERE location = ? ORDER BY id ASC";

    private static final String GET_RESTAURANT_BY_ID =
            "SELECT id, name, category, location, rating, priceForTwo, " +
            "deliveryTime, offer, imageUrl FROM restaurant WHERE id = ?";

    @Override
    public List<Restaurant> getAllRestaurants() {

        List<Restaurant> restaurants = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(GET_ALL_RESTAURANTS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                restaurants.add(mapTheResultSet(resultSet));
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Unable to retrieve all restaurants from the database.", e);
        }

        return restaurants;
    }

    @Override
    public List<Restaurant> getRestaurantsByCategory(String category) {

        List<Restaurant> restaurants = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(GET_RESTAURANTS_BY_CATEGORY)) {

            statement.setString(1, category);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    restaurants.add(mapTheResultSet(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Unable to retrieve restaurants by category.", e);
        }

        return restaurants;
    }

    @Override
    public List<Restaurant> getRestaurantsByLocation(String location) {

        List<Restaurant> restaurants = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(GET_RESTAURANTS_BY_LOCATION)) {

            statement.setString(1, location);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    restaurants.add(mapTheResultSet(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Unable to retrieve restaurants by location.", e);
        }

        return restaurants;
    }

    @Override
    public Restaurant getRestaurantById(int id) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(GET_RESTAURANT_BY_ID)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapTheResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Unable to retrieve restaurant with ID: " + id, e);
        }

        return null;
    }

    private Restaurant mapTheResultSet(ResultSet resultSet)
            throws SQLException {

        return new Restaurant(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("category"),
                resultSet.getString("location"),
                resultSet.getDouble("rating"),
                resultSet.getInt("priceForTwo"),
                resultSet.getString("deliveryTime"),
                resultSet.getString("offer"),
                resultSet.getString("imageUrl")
        );
    }
}
