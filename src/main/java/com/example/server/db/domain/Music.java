package com.example.server.db.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "")
public class Music {

    @Id
    private Integer id;

    private String title;
    private String artist;
    private String genre;
    private String album;


}
