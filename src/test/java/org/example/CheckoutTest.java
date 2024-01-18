package org.example;

import org.example.checkout.Checkout;
import org.example.checkout.Goods;
import org.example.exception.UnknownItemException;
import org.example.rules.QuantityPriceRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    private Goods goods = new Goods();
    private Checkout checkout = new Checkout(goods);

    private static final String PRODUCT_A="A", PRODUCT_B="B", PRODUCT_C="C", PRODUCT_D="D";
    private static final String UNKNOWN_PRODUCT = "Unknown";

    @BeforeEach
    public void initData() {
        goods.addItem("A", 50);
        goods.addItem("B", 30);
        goods.addItem("C", 20);
        goods.addItem("D", 15);

        checkout.addRule("A", new QuantityPriceRule(3, 130));
        checkout.addRule("B", new QuantityPriceRule(2,45));

    }
    @Test
    public void testTotalPrices() throws UnknownItemException {
        checkout.scan("A");
        assertEquals(BigDecimal.valueOf(50.0), checkout.getTotal());
        checkout.scan("B");
        assertEquals(BigDecimal.valueOf(80.0), checkout.getTotal());
        checkout.scan("A");
        assertEquals(BigDecimal.valueOf(130.0), checkout.getTotal());
        checkout.scan("A");
        assertEquals(BigDecimal.valueOf(160.0), checkout.getTotal());
        checkout.scan("B");
        assertEquals(BigDecimal.valueOf(175.0), checkout.getTotal());
    }

    @Test
    public void unknownItemScanner() {
        assertThrows(UnknownItemException.class,
                ()->{
                    checkout.scan(UNKNOWN_PRODUCT);
                    checkout.getTotal();
                });
    }

}