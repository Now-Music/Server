package com.example.server.db.domain;

import lombok.Data;

import org.springframework.data.annotation.Id;

@Data
//@Entity
public class Genre {

    @Id
    private String type;

}
