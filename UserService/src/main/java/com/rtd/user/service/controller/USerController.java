package com.rtd.user.service.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rtd.user.service.entities.Hotel;
import com.rtd.user.service.entities.Rating;
import com.rtd.user.service.entities.User;
import com.rtd.user.service.external.services.HotelService;
import com.rtd.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class USerController {

    private final UserService userService;
    private final RestTemplate restTemplate;
    
    @Autowired
    private HotelService hotelService;

    private final org.slf4j.Logger logger=LoggerFactory.getLogger(USerController.class);

    @GetMapping("/hello")
    public String hello(){
        return "hello ";
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1=userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    int retryCount=1;

    @GetMapping("/{userId}")
    // @CircuitBreaker(name = "ratingHotelaBreaker",fallbackMethod = "ratingHotelFallback")
    // @Retry(name ="ratingHotelService",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter",fallbackMethod ="ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        logger.info("Retry count :{}",retryCount);
        retryCount++;
        User user1=userService.getUser(userId);
        Rating[] ratingsOfUser=restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+userId,Rating[].class);
        List<Rating> ratings=Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList= ratings.stream().map(rating->{
            //api call to hotel service to get the hotel
            // http://localhost:8082/hotels/44fea622-b03e-446f-9660-b43159d5c2da

            // ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(),Hotel.class);
            // Hotel hotel=forEntity.getBody();
            Hotel hotel=hotelService.getHotel(rating.getHotelId());
            //set the hotel to the rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());
        user1.setRatings(ratingList);
        return ResponseEntity.ok(user1);
    }

    
    //creating fall back method for circuitbreaker
    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
        logger.info("fallback is executed because service is down :",ex.getMessage());
        User user=User.builder().email("dummy@gmail.ocm")
        .name("Dummy").about("this user is created dummy because some services are down").build();
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUsers=userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

}
