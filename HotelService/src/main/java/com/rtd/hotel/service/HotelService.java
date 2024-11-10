package com.rtd.hotel.service;

import java.util.List;

import com.rtd.hotel.entities.Hotel;

public interface HotelService {

    Hotel create(Hotel hotel);
    
    List<Hotel> getAll();

    Hotel getHotel(String id);

}
