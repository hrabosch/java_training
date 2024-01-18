package org.example.checkout;

import java.math.BigDecimal;

public record Item(String sku, BigDecimal price) {
}
