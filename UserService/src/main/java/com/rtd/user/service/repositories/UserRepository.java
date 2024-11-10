package com.rtd.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rtd.user.service.entities.User;

public interface UserRepository extends JpaRepository<User,String> {

}
