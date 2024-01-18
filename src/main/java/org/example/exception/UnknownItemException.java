package org.example.exception;

public class UnknownItemException extends Throwable {
    public UnknownItemException(String sku) {
        super(sku);
    }
}
