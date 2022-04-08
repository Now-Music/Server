package com.example.server.db.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendMusic {

    private String title;
    private String artist;
    private String album;

}
