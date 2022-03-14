package com.example.server.db.repository;

import com.example.server.db.domain.Music;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MusicRepository extends MongoRepository<Music,Integer> {


}
