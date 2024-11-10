package com.rtd.hotel.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rtd.hotel.entities.Hotel;
import com.rtd.hotel.exceptions.ResourceNotFoundException;
import com.rtd.hotel.repositories.HotelRepository;
import com.rtd.hotel.service.HotelService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String randomId = UUID.randomUUID().toString();
        hotel.setId(randomId);
        return hotelRepository.save(hotel) ;
    }

    @Override
    public List<Hotel> getAll() {
        // TODO Auto-generated method stub
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String id) {
        
        return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("hotel with given id not found:"+id));
    }

}
