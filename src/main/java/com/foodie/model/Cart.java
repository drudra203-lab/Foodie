
package com.foodie.model;

import java.util.HashMap;

public class Cart {

    private HashMap<Integer, CartItem> items;
    private int restaurantId;

    public Cart() {
        items = new HashMap<>();
    }

    public Cart(int restaurantId) {
        this.restaurantId = restaurantId;
        items = new HashMap<>();
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void addItem(CartItem item) {
        int itemId = item.getId();

        if (items.containsKey(itemId)) {
            CartItem existingItem = items.get(itemId);
            existingItem.setQuantity(
                existingItem.getQuantity() + item.getQuantity()
            );
        } else {
            items.put(itemId, item);
        }
    }

    public void updateItem(int itemId, int quantity) {
        if (items.containsKey(itemId)) {
            if (quantity <= 0) {
                items.remove(itemId);
            } else {
                items.get(itemId).setQuantity(quantity);
            }
        }
    }

    public void removeItem(int itemId) {
        items.remove(itemId);
    }

    public HashMap<Integer, CartItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0;

        for (CartItem item : items.values()) {
            total += item.getTotalPrice();
        }

        return total;
    }

    public void clear() {
        items.clear();
    }
}
