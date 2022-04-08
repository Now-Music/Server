package com.example.server.db.controller;

import com.example.server.db.domain.*;
import com.example.server.db.repository.MusicRepository;
import com.example.server.db.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MusicRepository musicRepository;
    @Autowired
    private static final List<RecommendMusic> rm = new ArrayList<>();


    /**
     * client로 부터 추천 음악 요청 들어 온 경우
     * 이미 로그인은 된 상태라고 판단.
     * 감정 분석 할 수 있도록 python 서버로 다시 보내준다.
     */
    @PostMapping("")
    public List<RecommendMusic> recommedMusics(@RequestBody ClientInfo clientInfo) throws JsonProcessingException {

        String userId = clientInfo.getUser_id();
        User loginUser = userRepository.findByUserId(userId);
        clientInfo.setMonth(clientInfo.getMonth());
        clientInfo.setGenres(loginUser.getFavoriteGenre());
        clientInfo.setUser_age(loginUser.getAge());


        try{
            RestTemplate restTemplate = new RestTemplate();

            // 요청 메시지에 필요한 파라미터들
            String url = "";

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            MultiValueMap<String,ClientInfo> body = new LinkedMultiValueMap<>();
            body.add("data",clientInfo);

            // 요청 메시지
            HttpEntity<?> requestMessage = new HttpEntity<>(body,httpHeaders);

            // 응답 메시지 ( 노래 리스트가 오겠지?)
//            HttpEntity<String> response = restTemplate.postForEntity(url,requestMessage,String.class);
            HttpEntity<String> response = restTemplate.postForEntity(url,requestMessage,String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode js = objectMapper.readTree(response.getBody());
            System.out.println(js.get("id_list"));

            List<String> musicIdLists = js.findValuesAsText("id_list");

            //JsonNode musicIdLists = js.get("id_list");

            //List<RecommendMusic> list = new ArrayList<>();

            //                Music music = musicRepository.findByMusicId(id);
//            musicIdLists.forEach(id ->{
//                Music findMusic = musicRepository.findById(musicIdLists.get(Integer.parseInt(id)));
//                RecommendMusic recommendMusic = new RecommendMusic(findMusic.getTitle(), findMusic.getArtist(), findMusic.getAlbum());
//                rm.add(recommendMusic);
//            });

            rm.add(new RecommendMusic("song1","beomsic","album1"));
            rm.add(new RecommendMusic("song2","hwan","album2"));
            rm.add(new RecommendMusic("song4","joon","album3"));
            rm.add(new RecommendMusic("song3","yoon","album4"));
            rm.add(new RecommendMusic("song5","beom","album5"));
            rm.add(new RecommendMusic("song6","kang","album6"));

            return rm;
        }catch (Exception e)
        {
            return rm;
        }

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
