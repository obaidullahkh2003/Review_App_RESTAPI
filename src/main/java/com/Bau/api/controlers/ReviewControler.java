package com.Bau.api.controlers;

import com.Bau.api.dto.ReviewDto;
import com.Bau.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewControler {
    private ReviewService reviewService;
    @Autowired
    public ReviewControler(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/pokemon/{pokemonId}/reviews")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "pokemonId") int pokemonId, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.createReview(pokemonId, reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/pokemon/{pokemonId}/reviews")
    public List<ReviewDto> getReviewsByPokemonId(@PathVariable(value = "pokemonId") int pokemonId) {
        return reviewService.getReviewsByPokemonId(pokemonId);
    }

    @GetMapping("/pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewsByReviewId(@PathVariable(value = "reviewId") int reviewId, @PathVariable(value = "pokemonId") int pokemonId) {
        ReviewDto reviewDto =reviewService.getReviewById(pokemonId,reviewId);
        return new ResponseEntity<>(reviewDto,HttpStatus.OK);
    }

    @PutMapping("/pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "reviewId") int reviewId, @PathVariable(value = "pokemonId") int pokemonId, @RequestBody ReviewDto reviewDto) {
        ReviewDto updatedReview = reviewService.updateReview(pokemonId,reviewId,reviewDto);
        return new ResponseEntity<>(updatedReview,HttpStatus.OK);
    }

    @DeleteMapping("/pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "reviewId") int reviewId, @PathVariable(value = "pokemonId") int pokemonId) {
        reviewService.deleteReview(pokemonId,reviewId);
        return new ResponseEntity<>("Review deleted",HttpStatus.OK);
    }
}
