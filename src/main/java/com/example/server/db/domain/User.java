package com.example.server.db.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Users")
public class User {

    @Id
    private Integer id;

    private String userId;
    private String password;
    private String name;
    private String age;

    private String favoriteGenre;

//    private List<UserGenre> favoriteGenre;

    private List<Integer> likeMusicIds;
    private List<Integer> dislikeMusicIds;
    private List<Integer> nowPlaying;

    public User(Integer id, String userId,String password,String name,String age, String favoriteGenre)
    {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.favoriteGenre = favoriteGenre;
        this.likeMusicIds = new ArrayList<>();
        this.dislikeMusicIds = new ArrayList<>();
        this.nowPlaying = new ArrayList<>();
    }
}
