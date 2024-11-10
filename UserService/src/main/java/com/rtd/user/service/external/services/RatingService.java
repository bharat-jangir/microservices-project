package com.rtd.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.rtd.user.service.entities.Rating;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {
    
    //get

    //post

    @PostMapping("/ratings")
    Rating createRating(Rating values);

    //put 

    @PutMapping("/ratings/{ratingId}")
    Rating updateRating(@PathVariable String ratingId,Rating rating);


    //delete

    @DeleteMapping("/ratings/{ratingId}")
    void deleteRating(@PathVariable String ratingId);
}
