package com.example.server.db.repository;

import com.example.server.db.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,Integer> {

    List findByIdAndPassword(String loginId, String password);
}
