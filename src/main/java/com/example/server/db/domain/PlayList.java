package com.example.server.db.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Document(collection = "nowPlaying")
/**
 * 추천받은 노래들로 현재 플레이중인 playlist
 */
public class PlayList {
    @Id
    private int id;

    private List<Integer> musicIdList;

}
