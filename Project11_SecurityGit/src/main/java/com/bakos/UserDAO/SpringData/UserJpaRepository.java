package com.bakos.UserDAO.SpringData;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakos.UserDTO.Users;

public interface UserJpaRepository extends JpaRepository<Users, Integer>{

}
