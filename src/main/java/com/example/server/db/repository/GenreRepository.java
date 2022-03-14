package com.example.server.db.repository;

import com.example.server.db.domain.MusicGenre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<MusicGenre,String> {
}
