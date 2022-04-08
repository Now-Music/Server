package com.example.server.db.controller;

import com.example.server.db.domain.Music;
import com.example.server.db.domain.MusicGenre;
import com.example.server.db.repository.GenreRepository;
import com.example.server.db.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/db/music")
public class MusicController {

    @Autowired
    MusicRepository musicRepository;

    @Autowired
    GenreRepository genreRepository;

    private Map<Integer,Object> recommedMusics = new HashMap<>();

    /**
     * 모든 노래 정보 반환
     */
    @GetMapping("/all")
    public ResponseEntity<List> getByAll()
    {
        List<Music> musicList = new ArrayList<>();
        try{
            musicList.addAll(musicRepository.findAll());
            return  new ResponseEntity<>(musicList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * id 값을 통해 맞는 음악 반환.
     */
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer id)
    {
        try
        {
            Optional musicOptional = musicRepository.findById(id);

            return new ResponseEntity<>(musicOptional.get(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 추천 받은 노래들의 id list 를 통해서
     * 추천 받은 노래들의 리스트를 만들어 반환.
     */
    @GetMapping("/{idLists}")
    public ResponseEntity<Map> getByIdList(@PathVariable("idList") List<Integer> idLists)
    {
        recommedMusics = new HashMap<>();
        Integer count = 0;
        try{
            for (Integer id : idLists) {
                Optional musicOptional = musicRepository.findById(id);
                recommedMusics.put(count,musicOptional);
                count++;
            }

            return new ResponseEntity<>(recommedMusics,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 처음 모든 노래 정보 DB에 저장.
     */
    @PostMapping("/all")
    public ResponseEntity<List> addMusics(@RequestBody List<Music> musics )
    {
        List musicList = musicRepository.findAll();
        int i = 0;
        try
        {
            System.out.println(" musics size : "+musics.size());
            for (Music music : musics) {
                //System.out.println(i);
                // 음악의 장르 정보 찾아서 저장.
                if(music.getGenre() == null || music.getGenre().equals("")) continue;
                String genreId = music.getGenre();
                if(genreId == null || genreId.equals("")) continue;
                Optional optionalGenre = genreRepository.findById(genreId);
                if(optionalGenre.equals(Optional.empty()))
                {
                    System.out.println("repo error");
                    continue;
                }
                MusicGenre mg = (MusicGenre) optionalGenre.get();

                Music newMusic = new Music(music.getId(),music.getTitle(),music.getArtist(),mg.getGenre(),music.getAlbum());
                Music saveMusic = musicRepository.save(newMusic);
                //musicList.add(saveMusic);
                //i+=1;
            }
            return new ResponseEntity<>(musicList, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            System.out.println("오류");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 처음 모든 노래 장르 정보 DB에 저장.
     */
    @PostMapping("/genres")
    public ResponseEntity<List> addGenres(@RequestBody List<MusicGenre> genres )
    {
        List genreList = genreRepository.findAll();

        try
        {
            for (MusicGenre genre  : genres) {
                MusicGenre newGenre = new MusicGenre(genre.getId(),genre.getGenre());
                MusicGenre saveGenre = genreRepository.save(newGenre);
                genreList.add(saveGenre);
            }
            return new ResponseEntity<>(genreList, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
