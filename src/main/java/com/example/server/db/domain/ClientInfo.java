package com.example.server.db.domain;

import lombok.Data;

@Data
public class ClientInfo {

    // 클라이언트에게 받은 부가정보들
    String user_id;
    String weather;
    String month;
    String picture_base64;
    String state;

    String genres;
    String user_age;


}
