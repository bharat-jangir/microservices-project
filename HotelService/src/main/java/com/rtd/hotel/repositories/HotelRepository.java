package com.rtd.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rtd.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,String> {

}
