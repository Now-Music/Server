package com.example.server.db.repository;

import com.example.server.db.domain.Music;
import com.example.server.db.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface MusicRepository extends MongoRepository<Music,Integer> {
//    Music findByMusicId(String musicId);
}
