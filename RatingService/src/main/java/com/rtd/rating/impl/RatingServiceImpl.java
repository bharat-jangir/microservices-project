package com.rtd.rating.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rtd.rating.entities.Rating;
import com.rtd.rating.repositories.RatingRepository;
import com.rtd.rating.services.RatingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Override
    public Rating create(Rating rating) {
        String randomRatingId = UUID.randomUUID().toString();
        rating.setRatingId(randomRatingId);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
    
}
