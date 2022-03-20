package com.example.server.db.controller;

import com.example.server.db.domain.ClientInfo;
import com.example.server.db.domain.MusicList;
import com.example.server.db.domain.User;
import com.example.server.db.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    UserRepository userRepository;

    /**
     * client로 부터 추천 음악 요청 들어 온 경우
     * 이미 로그인은 된 상태라고 판단.
     * 감정 분석 할 수 있도록 python 서버로 다시 보내준다.
     */
    @PostMapping("")
    public String recommedMusics(@RequestBody ClientInfo clientInfo) throws JsonProcessingException {
        String userId = clientInfo.getUser_id();

        User loginedUser = userRepository.findByUserId(userId);

        clientInfo.setGenres(loginedUser.getFavoriteGenre());
        clientInfo.setUser_age(loginedUser.getAge());

        // 이제 python 서버에 보내기.(clientInfo 정보)

        RestTemplate restTemplate = new RestTemplate();

        // 요청 메시지에 필요한 파라미터들
        String url = "http://{일준이}";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String,ClientInfo> body = new LinkedMultiValueMap<>();
        body.add("data",clientInfo);

        // 요청 메시지
        HttpEntity<?> requestMessage = new HttpEntity<>(body,httpHeaders);

        // 응답 메시지 ( 노래 리스트가 오겠지?)
        HttpEntity<String> response = restTemplate.postForEntity(url,requestMessage,String.class);

        // 추천된 노래 리스트들.
        ObjectMapper objectMapper = new ObjectMapper();
        MusicList recommendList = objectMapper.readValue(response.getBody(), MusicList.class);

        // 다시 client에 노래 리스트(정보들) 보내주기.
        restTemplate = new RestTemplate();

        url = "http://{윤종}";
        MultiValueMap<String,MusicList> returnBody = new LinkedMultiValueMap<>();
        returnBody.add("List",recommendList);

        // 최종 응답 메시지
        HttpEntity<String> returnResponse = restTemplate.postForEntity(url,requestMessage,String.class);
        log.info("response = {}",returnResponse);
        return "ok";
    }

    /**
     * 노래 좋아요 누른 경우.
     * user 정보에 추가해줘야 한다.
     */
    @PostMapping("/like")
    public String AddLikeMusic(@RequestBody MusicList musicList)
    {
        User user = userRepository.findByUserId(musicList.getUserId());
        for (Integer musicId : musicList.getMusicIdList()) {
            boolean isContainId = user.getLikeMusicIds().contains(musicId);
            if(!isContainId) user.getLikeMusicIds().add(musicId);
        }
        // 저장.
        userRepository.save(user);
        return "like";
    }

    /**
     * 노래 싫어요 누른 경우.
     * user 정보에 추가해줘야 한다.
     */
    @PostMapping("/dislike")
    public String AddDisLikeMusic(@RequestBody MusicList musicList)
    {
        User user = userRepository.findByUserId(musicList.getUserId());
        for (Integer musicId : musicList.getMusicIdList()) {
            boolean isContainId = user.getDislikeMusicIds().contains(musicId);
            if(!isContainId) user.getDislikeMusicIds().add(musicId);
        }
        // 저장.
        userRepository.save(user);
        return "like";
    }




}
