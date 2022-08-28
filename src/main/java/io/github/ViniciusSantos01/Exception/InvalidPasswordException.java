package io.github.ViniciusSantos01.Exception;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() {
        super("Invalid password.");
    }
}
