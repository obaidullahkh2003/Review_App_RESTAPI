package com.Bau.api.repository;

import com.Bau.api.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByPokemonId(int id);
}
