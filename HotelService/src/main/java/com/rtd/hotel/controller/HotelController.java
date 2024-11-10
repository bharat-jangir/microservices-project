package com.rtd.hotel.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rtd.hotel.entities.Hotel;
import com.rtd.hotel.service.HotelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }


    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> createHotel(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(hotelId));
    }


    @GetMapping
    public ResponseEntity<List<Hotel>> getAll(){
        return ResponseEntity.ok(hotelService.getAll());
    }
}
