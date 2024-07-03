package com.Bau.api.controlers;

import com.Bau.api.dto.PokemonResponse;
import com.Bau.api.dto.Pokemondto;
import com.Bau.api.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/")
public class PokemonControler {

    private PokemonService pokemonService;
    @Autowired
    public PokemonControler(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("pokemons")
    public ResponseEntity<PokemonResponse> getPokemons(@RequestParam(value = "pagenum",defaultValue = "0",required = false) int pagenum , @RequestParam(value = "pagesize",defaultValue = "10",required = false) int pagesize) {
      return new ResponseEntity<>(pokemonService.getpokemons(pagenum,pagesize),HttpStatus.OK);
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<Pokemondto> getPokemon(@PathVariable int id) {
        return ResponseEntity.ok(pokemonService.getpokemonbyid(id));
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pokemondto> createPokemon(@RequestBody Pokemondto pokemondto) {
        return new ResponseEntity<>(pokemonService.createpokemon(pokemondto),HttpStatus.CREATED);
    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<Pokemondto> updatePokemon(@PathVariable("id") int id, @RequestBody Pokemondto pokemondto) {
        Pokemondto response = pokemonService.updatepokemon(pokemondto,id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") int pokemonid) {
        pokemonService.deletepokemon(pokemonid);
        return new ResponseEntity<>("pokemon deleted",HttpStatus.OK);
    }



}
