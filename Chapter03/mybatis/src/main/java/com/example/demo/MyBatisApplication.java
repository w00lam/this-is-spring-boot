package com.example.demo;

import com.example.demo.mapper.ArticleMapper;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.model.Article;
import com.example.demo.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyBatisApplication implements ApplicationRunner {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        var members = memberMapper.selectAll();
//        log.info("회원 목록 = {}", members);
//        log.info("========");
//        var member = memberMapper.selectById(2L);
//        log.info("2번 회원 = {}", member);

//        var articles = articleMapper.selectAll();
//        log.info("게시글 목록 = {}", articles);
//        log.info("========");
//        var article = articleMapper.selectById(1L);
//        log.info("게시글 목록 = {}", article);
        int count = memberMapper.selectAllCount();
        log.info("Member count = {}", count);

        Member member = memberMapper
                .selectByEmail("SeojunYoon@hanbit.co.kr")
                .orElseThrow();
        log.info("Member: {}", member);
        Article article = Article.builder()
                .title("Hello, MyBatis")
                .description("MyBatis is an SQL Mapper framework")
                .memberId(member.getId())
                .build();
        int inserted = articleMapper.insert(article);
        log.info("Inserted: {}", inserted);
    }
}
