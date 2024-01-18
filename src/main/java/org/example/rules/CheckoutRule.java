package org.example.rules;

import java.math.BigDecimal;

public interface CheckoutRule {

    BigDecimal applyRule(String sku, Integer quantity, BigDecimal basicPrice);
}
