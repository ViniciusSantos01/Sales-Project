package io.github.ViniciusSantos01.Exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException() {
        super("Order not found.");
    }
}
