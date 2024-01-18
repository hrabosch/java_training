package org.example.checkout;

import org.example.exception.UnknownItemException;
import org.example.rules.CheckoutRule;
import org.example.rules.DefaultPriceRule;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Object to keep ordered items which are available from datasource (to keep it simple, @{@link Goods} instance is used as datasource).
 * Checkout can be extended by additional rules, which will be applied at the end of calculation based on SKUs.
 * NOTE: Current implementation holds only ONE implementation of @{@link CheckoutRule} per SKU.
 *       Holding multiple rules for single SKU has to be resolved in which order and when should be applied.
 */
public class Checkout {

    private final Map<String, Integer> cart = new HashMap<>();
    private final Map<String, CheckoutRule> activeRules = new HashMap<>();

    private static final DefaultPriceRule defaultPriceRule = DefaultPriceRule.getInstance();

    private final Goods goods;

    public Checkout(Goods goods) {
        this.goods = goods;
    }

    /**
     * In case of allowed multiple rules for unique SKU (possibly additional percentage rule?),
     * we can keep Collection of Rules per every SKU (Key) in our {@link #activeRules}.
     */
//    public void addRule(String sku, CheckoutRule rule) {
//        appliedRules.compute(sku, (k, v) -> {
//            if (v == null) return List.of(rule);
//            else {
//                v.add(rule);
//                return v;
//            }
//        });
//    }

    public void addRule(String sku, CheckoutRule rule) {
        activeRules.put(sku, rule);
    }

    public void scan(String sku) throws UnknownItemException {
        if (goods.hasItem(sku)) {
            cart.merge(sku, 1, Integer::sum);
        } else {
            throw new UnknownItemException(sku);
        }
    }

    public BigDecimal getTotal() {
        return cart.entrySet()
                .stream()
                .map(e -> activeRules.getOrDefault(e.getKey(), defaultPriceRule).applyRule(e.getKey(), e.getValue(), goods.getPrice(e.getKey())))
                .collect(Collectors.reducing(BigDecimal.ZERO, BigDecimal::add));
    }
}
