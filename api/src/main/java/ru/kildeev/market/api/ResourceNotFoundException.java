package ru.kildeev.market.api;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException (String message) {
        super(message);
    }
}
