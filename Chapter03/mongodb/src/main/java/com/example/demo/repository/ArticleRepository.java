package com.example.demo.repository;

import com.example.demo.model.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
    List<Article> findByName(String name);
    List<Article> findByNameAndEmail(String name, String email);
    List<Article> findByNameOrEmail(String name, String email);
    List<Article> findByNameStartingWith(String name);
    List<Article> findByNameEndingWith(String name);
    List<Article> findByNameContaining(String name);
    List<Article> findByNameLike(String name); // containing "name" equals to like "%name%"

    List<Article> findBy(TextCriteria criteria, Sort sort);
    List<Article> findBy(TextCriteria criteria, Pageable pageable);
    List<Article> findByOrderByScoreDesc(TextCriteria criteria);
    List<Article> findByEmail(String email, TextCriteria criteria, Sort sort);

    @Query("{name: ?0, email: ?1}")
    List<Article> findByAuthor(String name, String email);

    @Query("{name: ?0}")
    @Update("{$set: {email: ?1}}")
    int updateEmailByName(String name, String email);

}
