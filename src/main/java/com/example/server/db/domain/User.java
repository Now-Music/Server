package com.example.server.db.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


import org.springframework.data.annotation.Id;

import javax.annotation.Generated;
import java.util.List;

@Data
@Document(collection = "Users")
public class User {

    @Id
    private String id;
    private String password;
    private String name;
    private int age;

    private List<Genre> favoriteGenre;


    public User(String id,String password,String name,int age, List<Genre> favoriteGenre)
    {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.favoriteGenre = favoriteGenre;
    }
}
