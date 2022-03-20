package com.example.server.db.domain;

import lombok.Data;

import java.util.List;


@Data
/**
 * 추천받은 노래들 playList
 * 좋아하는 노래들 playList
 * 싫어하는 노래들 playList
 */
public class MusicList {

    private String userId;
    private List<Integer> musicIdList;

}
