package com.example.demo;

import com.example.demo.model.Article;
import com.example.demo.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MongoApplication implements ApplicationRunner {
    private final ArticleRepository articleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        TextCriteria criteria = TextCriteria
                .forDefaultLanguage()
                .matchingAny("제주", "성산일출봉", "바다");
        List<Article> result = articleRepository.findByOrderByScoreDesc(criteria);
        result.forEach(article -> log.info("article={}", article));

        int count = articleRepository.updateEmailByName("윤서준", "SeojunYoon@caompus.co.kr");
        log.info("count={}", count);

        var result2 = articleRepository.findByAuthor("윤서준", "SeojunYoon@campus.co.kr");
        result2.forEach(article -> log.info("article={}", article));
    }
}
