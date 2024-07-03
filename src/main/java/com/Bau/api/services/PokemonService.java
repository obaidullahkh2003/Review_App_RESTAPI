package com.Bau.api.services;

import com.Bau.api.dto.Pokemondto;
import com.Bau.api.dto.PokemonResponse;

public interface PokemonService {
    Pokemondto createpokemon(Pokemondto pokemondto);

    PokemonResponse getpokemons(int pagenum , int pagesize);

    Pokemondto getpokemonbyid(int id);

    Pokemondto updatepokemon(Pokemondto pokemondto,int id);

    void deletepokemon(int id);
}
