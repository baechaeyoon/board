package com.web.board.user.dao;

import com.web.board.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findById (String id);
}
