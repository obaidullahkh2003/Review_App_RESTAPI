package com.Bau.api.exception;

public class PokemonNotFounexception extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public PokemonNotFounexception(String message) {
        super(message);

    }
}
