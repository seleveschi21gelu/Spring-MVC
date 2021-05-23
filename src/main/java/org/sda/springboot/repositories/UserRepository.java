package org.sda.springboot.repositories;

import org.sda.springboot.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    public UserEntity findByUsername(String name);

}
