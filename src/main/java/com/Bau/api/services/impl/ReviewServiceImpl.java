package com.Bau.api.services.impl;

import com.Bau.api.dto.ReviewDto;
import com.Bau.api.exception.PokemonNotFounexception;
import com.Bau.api.exception.ReviewNotFoundException;
import com.Bau.api.model.Review;
import com.Bau.api.model.Pokemon;
import com.Bau.api.repository.PokemonRepository;
import com.Bau.api.repository.ReviewRepository;
import com.Bau.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public ReviewDto createReview(int pokemonId, ReviewDto reviewDto) {
        Review review = mapToEntity(reviewDto);

        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFounexception("Pokemon with associated review not found"));

        review.setPokemon(pokemon);

        Review newReview = reviewRepository.save(review);

        return mapToDto(newReview);
    }

    @Override
    public List<ReviewDto> getReviewsByPokemonId(int id) {
        List<Review> reviews = reviewRepository.findByPokemonId(id);

        return reviews.stream().map(review -> mapToDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(int reviewid, int pokemonid) {
        Pokemon pokemon = pokemonRepository.findById(pokemonid).orElseThrow(() -> new PokemonNotFounexception("Pokemon can not be found"));
        Review review = reviewRepository.findById(reviewid).orElseThrow(() -> new ReviewNotFoundException("Review can not be found"));
        if (review.getPokemon().getId() != pokemon.getId()) {
            throw new ReviewNotFoundException("Review with associated Pokemon not found");
        }
        return mapToDto(review);
    }

    @Override
    public ReviewDto updateReview(int pokemonid, int reviewid, ReviewDto reviewDto) {
        Pokemon pokemon = pokemonRepository.findById(pokemonid).orElseThrow(() -> new PokemonNotFounexception("Pokemon with associated review not found"));
        Review review = reviewRepository.findById(reviewid).orElseThrow(() -> new ReviewNotFoundException("Review can not be found"));
        if (review.getPokemon().getId() != pokemon.getId()) {
            throw new ReviewNotFoundException("Review with associated Pokemon not found");
        }
        review.setTitle(reviewDto.getTitle());
        review.setStars(reviewDto.getStars());
        review.setContent(reviewDto.getContent());

        Review newReview = reviewRepository.save(review);
        return mapToDto(newReview);
    }

    @Override
    public void deleteReview(int reviewid, int pokemonid) {
        Pokemon pokemon = pokemonRepository.findById(pokemonid).orElseThrow(() -> new PokemonNotFounexception("Pokemon with associated review not found"));
        Review review = reviewRepository.findById(reviewid).orElseThrow(() -> new ReviewNotFoundException("Review can not be found"));
        if (review.getPokemon().getId() != pokemon.getId()) {
            throw new ReviewNotFoundException("Review with associated Pokemon not found");
        }
        reviewRepository.delete(review);

    }


    private ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }

    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        return review;
    }
}
