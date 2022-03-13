//package com.example.server.db.controller;
//
//import com.example.server.db.domain.User;
//import com.example.server.db.repository.UserRepository;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//@CrossOrigin("http://localhost:8080")
//@RestController
//@RequestMapping("/db/user")
//public class UserController {
//
//    @Autowired
//    UserRepository userRepository;
//
////    @Autowired
////    UserService userService;
//
//    User loginedUser = new User();
//
//    /**
//     * 1. 유저 전체 리스트 검색
//     * 2. 파라미터로 넘어간 유저 리스트 검색
//     */
//    @GetMapping("/users")
//    public ResponseEntity<List> getAllUsers(@RequestParam(required = false) String userId,String password)
//    {
//        try
//        {
//            List userLists = new ArrayList<>();
//            // 파라미터로 아무런 아이디가 넘어오지 않은 경우는 전체 출력
//            if(userId == null || userId.isEmpty())
//            {
//                userLists.addAll(userRepository.findAll());
//            }
//            // 어떤 아이디가 넘어온 경우 ->  해당 아이디만 출력
//            else
//            {
//                userLists.addAll(userRepository.findByContainingIdAndPassword(userId,password));
//            }
//
//            if(userLists.isEmpty())
//            {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(userLists, HttpStatus.OK);
//        }
//        catch (Exception e)
//        {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    /**
//     * 유저 아이디를 이용해서 검색
//     *
//     */
//    @GetMapping("/users/{id}")
//    public ResponseEntity getUserById(@PathVariable("id") String id)
//    {
//        try
//        {
//            Optional userOptional = userRepository.findById(id);
//            return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
//        }
//        catch (Exception e)
//        {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    /**
//     *
//     * 로그인
//     */
////    @GetMapping("/login")
////    public ResponseEntity<User> login(@PathVariable("id") String id, String password)
////    {
////        try
////        {
////            List user = userRepository.login(id,password);
////            if(user == null)
////            {
////                return ResponseEntity.notFound().build();
////            }
////            else
////            {
////                return new ResponseEntity(user,HttpStatus.OK);
////            }
////        }
////        catch(Exception e)
////        {
////            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
//
//    // 유저 정보 등록
//    @PostMapping("/users")
//    public ResponseEntity addUser(@RequestBody User user)
//    {
//        try
//        {
//            User addedUser = userRepository.save(new User(user.getId(), user.getPassword(),
//                    user.getName(),user.getAge(),user.getFavoriteGenre()));
//            return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
//        }
//        catch (Exception e)
//        {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/users/{id}")
//    public ResponseEntity updateUser(@PathVariable("id") String id, @RequestBody User user)
//    {
//        Optional userOptional = userRepository.findById(id);
//
//        if(userOptional.isPresent())
//        {
//            User updatedUser = (User) userOptional.get();
//            updatedUser.setId(user.getId());
//            updatedUser.setPassword(user.getPassword());
//            updatedUser.setName(user.getName());
//            updatedUser.setAge(user.getAge());
//            updatedUser.setFavoriteGenre(user.getFavoriteGenre());
//            return new ResponseEntity<>(userRepository.save(updatedUser), HttpStatus.OK);
//        }
//        else
//        {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity deleteABook(@PathVariable("id") String id)
//    {
//        try
//        {
//            userRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        catch (Exception e)
//        {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//}


package com.example.server.db.controller;

import com.example.server.db.domain.Genre;
import com.example.server.db.domain.User;
import com.example.server.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/db/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    // 로그인 아이디 비밀번호에 맞는 유저 찾기.
    @GetMapping("/members")
    public ResponseEntity<List> getAllUsers(@RequestParam(required = false) String userId,String password)
    {
        try
        {
            List memberList = new ArrayList<>();
            if(userId == null || userId.isEmpty())
            {
                memberList.addAll(userRepository.findAll());
            }
            else
            {
                memberList.addAll(userRepository.findByIdAndPassword(userId,password));
            }

            if(memberList.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(memberList, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // id에 맞는 유저 찾기
    @GetMapping("/members/{id}")
    public ResponseEntity getUserById(@PathVariable("id") String id)
    {
        try
        {
            Optional bookOptional = userRepository.findById(id);

            return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //  유저 등록
    @PostMapping("/members")
    public ResponseEntity addUser(@RequestBody User user)
    {

        try
        {
            User newU = new User(user.getId(), user.getPassword(),user.getName(),user.getAge(),
                    user.getFavoriteGenre());
            User newUser  = userRepository.save(newU);

            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/members/{id}")
    public ResponseEntity updateUser(@PathVariable("id") String id, @RequestBody User user)
    {
        Optional userOptional = userRepository.findById(id);

        if(userOptional.isPresent())
        {
            User updatedUser = (User) userOptional.get();
            updatedUser.setId(user.getId());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setName(user.getName());
            updatedUser.setAge(user.getAge());
            updatedUser.setFavoriteGenre(user.getFavoriteGenre());
            return new ResponseEntity<>(userRepository.save(updatedUser), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") String id)
    {
        try
        {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
