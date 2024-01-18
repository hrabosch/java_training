package org.example.checkout;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Mostly mocked data class to simulate some sort of datasource.
 * This class is responsible to simulate some goods warehouse data like price, available quantity, etc.
 */
public class Goods {
    private final Map<String, Item> items = new HashMap<>();

    public void addItem(final String sku, final BigDecimal price) {
        items.put(sku, new Item(sku, price));
    }

    public void addItem(final String sku, final double price) {
        items.put(sku, new Item(sku, BigDecimal.valueOf(price)));
    }

    public boolean hasItem(String sku) {
        return items.containsKey(sku);
    }

    public BigDecimal getPrice(String sku) {
        return items.get(sku).price();
    }
}
