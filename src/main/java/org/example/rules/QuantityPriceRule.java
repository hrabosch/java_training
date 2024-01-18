package org.example.rules;

import java.math.BigDecimal;

/**
 * Quantity based rule definition.
 * Example: For each X items is price Y.
 */
public record QuantityPriceRule(Integer quantity, BigDecimal price) implements CheckoutRule{
    public QuantityPriceRule(Integer quantity, BigDecimal price) {
        this.quantity = quantity;
        this.price = price;
    }

    public QuantityPriceRule(Integer quantity, double price) {
        this(quantity, BigDecimal.valueOf(price));
    }

    @Override
    public BigDecimal applyRule(String sku, Integer sourceQuantity, BigDecimal basicPrice) {
        int quotient = sourceQuantity / quantity;
        int remainder = sourceQuantity % quantity;

        return price.multiply(BigDecimal.valueOf(quotient)).add(basicPrice.multiply(BigDecimal.valueOf(remainder)));
    }
}
