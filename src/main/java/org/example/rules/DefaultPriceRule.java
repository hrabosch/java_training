package org.example.rules;

import java.math.BigDecimal;

/**
 * Default rule for goods which has no special offer and should be calculated in default way.
 */
public class DefaultPriceRule implements CheckoutRule {

    private static DefaultPriceRule rule = null;
    public static DefaultPriceRule getInstance() {
        if (rule == null) {
            rule = new DefaultPriceRule();
        }
        return rule;
    }

    @Override
    public BigDecimal applyRule(String sku, Integer quantity, BigDecimal basicPrice) {
        return basicPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
