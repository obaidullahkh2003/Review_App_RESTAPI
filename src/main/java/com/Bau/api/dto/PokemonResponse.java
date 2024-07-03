package com.Bau.api.dto;

import lombok.Data;

import java.util.List;
@Data
public class PokemonResponse {
    private List<Pokemondto> data;
    private int pagenum;
    private int pagesize;
    private long totalelements;
    private long totalpages;
    private boolean last;
}
