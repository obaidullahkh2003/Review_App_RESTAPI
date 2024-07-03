package com.Bau.api.services.impl;

import com.Bau.api.dto.Pokemondto;
import com.Bau.api.dto.PokemonResponse;
import com.Bau.api.exception.PokemonNotFounexception;
import com.Bau.api.model.Pokemon;
import com.Bau.api.repository.PokemonRepository;
import com.Bau.api.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceimpl implements PokemonService {
    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceimpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public Pokemondto createpokemon(Pokemondto pokemondto) {
        Pokemon pokemon=mapTOEntity(pokemondto);
        Pokemon newpokemon = pokemonRepository.save(pokemon);
        Pokemondto newpokemondto=new Pokemondto();
        newpokemondto.setId(newpokemon.getId());
        newpokemondto.setName(pokemondto.getName());
        newpokemondto.setType(pokemondto.getType());
        return newpokemondto;
    }

    @Override
    public PokemonResponse getpokemons(int pagenum , int pagesize) {
        PageRequest pageRequest = PageRequest.of(pagenum,pagesize);
        Page<Pokemon> pokemons= pokemonRepository.findAll(pageRequest);
        List<Pokemon> list=pokemons.getContent();
        List<Pokemondto> content= list.stream().map(p -> mapTOdto(p)).collect(Collectors.toList());
        PokemonResponse pokmeonResponse=new PokemonResponse();
        pokmeonResponse.setData(content);
        pokmeonResponse.setPagenum(pokemons.getNumber());
        pokmeonResponse.setPagesize(pokemons.getSize());
        pokmeonResponse.setTotalelements(pokemons.getTotalElements());
        pokmeonResponse.setTotalpages(pokemons.getTotalPages());
        pokmeonResponse.setLast(pokemons.isLast());
        return pokmeonResponse;

    }

    @Override
    public Pokemondto getpokemonbyid(int id) {
        Pokemon pokemon= pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFounexception("Pokemon not Found"));
        return mapTOdto(pokemon);
    }

    @Override
    public Pokemondto updatepokemon(Pokemondto pokemondto,int id) {
        Pokemon pokemon= pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFounexception("Pokemon cant be updated "));
        pokemon.setName(pokemondto.getName());
        pokemon.setType(pokemondto.getType());
        Pokemon updatedpokemon = pokemonRepository.save(pokemon);
        return mapTOdto(updatedpokemon);
    }

    @Override
    public void deletepokemon(int id) {
        Pokemon pokemon= pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFounexception("Pokemon cant be deleted"));
        pokemonRepository.delete(pokemon);
    }

    private Pokemondto mapTOdto(Pokemon pokemon){
        Pokemondto pokemondto=new Pokemondto();
        pokemondto.setId(pokemon.getId());
        pokemondto.setName(pokemon.getName());
        pokemondto.setType(pokemon.getType());
        return pokemondto;
    }

    private Pokemon mapTOEntity(Pokemondto pokemondto){
        Pokemon pokemon=new Pokemon();
        pokemon.setName(pokemondto.getName());
        pokemon.setType(pokemondto.getType());
        return pokemon;
    }


}
