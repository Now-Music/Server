package com.example.server.db.domain;

import lombok.Data;

import org.springframework.data.annotation.Id;

@Data
public class UserGenre {

    @Id
    private String type;

}
