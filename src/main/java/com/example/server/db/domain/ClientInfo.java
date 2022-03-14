package com.example.server.db.domain;

import lombok.Data;

@Data
public class ClientInfo {

    // 부가정보들
    int user_age;
    String weather;
   // String month;
    String picture_base64;
    String state;

}
