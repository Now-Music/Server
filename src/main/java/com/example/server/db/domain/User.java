package com.example.server.db.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


import org.springframework.data.annotation.Id;

import javax.annotation.Generated;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Users")
public class User {

    @Id
    private Integer id;

    private String loginId;
    private String password;
    private String name;
    private int age;

    private List<Genre> favoriteGenre;


    public User(String loginId,String password,String name,int age, List<Genre> favoriteGenre)
    {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.favoriteGenre = favoriteGenre;
    }
}
