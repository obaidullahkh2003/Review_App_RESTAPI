package com.Bau.api.services;

import com.Bau.api.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(int pokemonId, ReviewDto reviewDto);
    List<ReviewDto> getReviewsByPokemonId(int id);
    ReviewDto getReviewById(int reviewid,int pokemonid);
    ReviewDto updateReview(int pokemonid,int reviewid,ReviewDto reviewDto);
    void deleteReview(int reviewid, int pokemonid);
}
