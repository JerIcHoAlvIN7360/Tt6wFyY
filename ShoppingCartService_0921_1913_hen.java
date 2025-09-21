// 代码生成时间: 2025-09-21 19:13:40
package com.example.shoppingcart;

import io.quarkus.runtime.annotations.RegisterForReflection;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RegisterForReflection
@ApplicationScoped
public class ShoppingCartService {

    // Map to store the shopping cart items
    private final Map<UUID, ShoppingCart> carts = new HashMap<>();

    // Adds an item to the shopping cart
    public ShoppingCart addItemToCart(UUID cartId, Item item) {
        ShoppingCart cart = getCart(cartId);
        cart.addItem(item);
        return cart;
    }

    // Removes an item from the shopping cart
    public ShoppingCart removeItemFromCart(UUID cartId, UUID itemId) {
        ShoppingCart cart = getCart(cartId);
        cart.removeItem(itemId);
        return cart;
    }

    // Checks out the shopping cart and returns the total amount
    public CheckoutResponse checkoutCart(UUID cartId) {
        ShoppingCart cart = getCart(cartId);
        if (cart == null) {
            throw new NotFoundException("Shopping cart not found");
        }
        double total = cart.calculateTotal();
        carts.remove(cartId);
        return new CheckoutResponse(total);
    }

    // Retrieves a shopping cart based on the cart ID
    private ShoppingCart getCart(UUID cartId) {
        return carts.getOrDefault(cartId, createNewCart(cartId));
    }

    // Creates a new shopping cart
    private ShoppingCart createNewCart(UUID cartId) {
        ShoppingCart newCart = new ShoppingCart(cartId);
        carts.put(cartId, newCart);
        return newCart;
    }
}

/**
 * ShoppingCart.java
 *
 * Represents a shopping cart and its operations.
 */
package com.example.shoppingcart;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShoppingCart {
    private final UUID id;
    private final List<Item> items = new ArrayList<>();

    public ShoppingCart(UUID id) {
        this.id = id;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(UUID itemId) {
        items.removeIf(item -> item.getId().equals(itemId));
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }

    public List<Item> getItems() {
        return items;
    }

    public UUID getId() {
        return id;
    }
}

/**
 * Item.java
 *
 * Represents an item that can be added to the shopping cart.
 */
package com.example.shoppingcart;

public class Item {
    private final UUID id;
    private final String name;
    private final double price;

    public Item(UUID id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

/**
 * CheckoutResponse.java
 *
 * Represents the response received after checking out.
 */
package com.example.shoppingcart;

public class CheckoutResponse {
    private final double totalAmount;

    public CheckoutResponse(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}