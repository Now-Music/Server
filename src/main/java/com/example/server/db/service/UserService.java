//package com.example.server.db.service;
//
//import com.example.server.db.domain.User;
//import com.example.server.db.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.repository.query.FluentQuery;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//
//@Service()
//public class UserService implements UserRepository {
//
//    @Autowired
//    MongoTemplate mongoTemplate = null;
//
//    @Override
//    public List findByIdAndPassword(String id, String password) {
//        return null;
//    }
//
//    @Override
//    public <S extends User> S save(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends User> List<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public Optional<User> findById(String s) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(String s) {
//        return false;
//    }
//
//    @Override
//    public List<User> findAll() {
//        return null;
//    }
//
//    @Override
//    public Iterable<User> findAllById(Iterable<String> strings) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(String s) {
//
//    }
//
//    @Override
//    public void delete(User entity) {
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends String> strings) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends User> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    @Override
//    public List<User> findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    public Page<User> findAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends User> S insert(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends User> List<S> insert(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public <S extends User> Optional<S> findOne(Example<S> example) {
//        return Optional.empty();
//    }
//
//    @Override
//    public <S extends User> List<S> findAll(Example<S> example) {
//        return null;
//    }
//
//    @Override
//    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
//        return null;
//    }
//
//    @Override
//    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends User> long count(Example<S> example) {
//        return 0;
//    }
//
//    @Override
//    public <S extends User> boolean exists(Example<S> example) {
//        return false;
//    }
//
//    @Override
//    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//        return null;
//    }
//
////    @Override
////    public User login(String Id, String password) {
////        Query query = new Query(new Criteria("id").is(Id)
////                .and("password").is(password));
////        List<User> result = mongoTemplate.find(query,
////                User.class,"user");
////        if(result.size()>0)
////        {
////            return result.get(0);
////        }
////        else return null;
////    }
//
////    @Override
////    public void joinMember(User user) {
////        mongoTemplate.insert(user,"user");
////    }
//
//}
