
package com.foodie.daoImple;

import com.foodie.dao.MenuDao;
import com.foodie.dbconnection.DBConnection;
import com.foodie.model.MenuItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl implements MenuDao {

	private static final String GET_MENUITEMS_BY_RESTAURANT = "SELECT id, restaurantId, itemName, description, price, "
			+ "category, isVeg, imageUrl FROM menuitems " + "WHERE restaurantId = ? ORDER BY category, id";

	private static final String GET_MENU_BY_CATEGORY = "SELECT id, restaurantId, itemName, description, price, "
			+ "category, isVeg, imageUrl FROM menuitems " + "WHERE restaurantId = ? AND category = ? ORDER BY id";

	private static final String GET_MENU_BY_MENUID = "SELECT id, restaurantId, itemName, description, price, "
			+ "category, isVeg, imageUrl FROM menuitems WHERE id = ?";

	@Override
	public List<MenuItem> getMenuByRestaurantId(int restaurantId) {

		List<MenuItem> list = new ArrayList<>();

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(GET_MENUITEMS_BY_RESTAURANT)) {

			statement.setInt(1, restaurantId);

			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					list.add(mapRow(resultSet));
				}
			}

		} catch (SQLException e) {
			throw new IllegalStateException("Unable to retrieve menu items for restaurant ID: " + restaurantId, e);
		}

		return list;
	}

	@Override
	public List<MenuItem> getMenuByCategory(int restaurantId, String category) {

		List<MenuItem> list = new ArrayList<>();

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(GET_MENU_BY_CATEGORY)) {

			statement.setInt(1, restaurantId);
			statement.setString(2, category);

			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					list.add(mapRow(resultSet));
				}
			}

		} catch (SQLException e) {
			throw new IllegalStateException("Unable to retrieve menu items for category: " + category, e);
		}

		return list;
	}

	@Override
	public MenuItem getMenuItemById(int id) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(GET_MENU_BY_MENUID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {

				if (resultSet.next()) {
					return mapRow(resultSet);
				}
			}

		} catch (SQLException e) {
			throw new IllegalStateException("Unable to retrieve menu item with ID: " + id, e);
		}

		return null;
	}

	private MenuItem mapRow(ResultSet rs) throws SQLException {

		double price = rs.getDouble("price");
		if (rs.wasNull()) {
			price = 0.0;
		}

		boolean isVeg = rs.getBoolean("isVeg");
		if (rs.wasNull()) {
			isVeg = false;
		}

		return new MenuItem(rs.getInt("id"), rs.getInt("restaurantId"), rs.getString("itemName"),
				rs.getString("description"), price, rs.getString("category"), isVeg, rs.getString("imageUrl"));
	}
}
