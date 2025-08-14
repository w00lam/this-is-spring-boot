package com.example.demo;

import com.example.demo.model.Article;
import com.example.demo.model.Member;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class JpaApplication implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final ArticleRepository articlerepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var member1 = Member.builder()
                .name("팔덕구")
                .email("duck98@naver.com")
                .age(28).build();
        log.info("save 팔덕구");
        memberRepository.save(member1);
        log.info("saved {}", member1);

        var article1 = Article.builder()
                .title("내일은 광복절")
                .description("오늘은 열심히 놀아야겠다")
                .member(member1).build();
        articlerepository.save(article1);

        var articles = articlerepository.findAll();
        for (Article article : articles) {
            log.info("{}", article);
        }

        var member2 = Member.builder()
                .name("구덕팔")
                .email("duck89@naver.com")
                .age(26).build();
        log.info("save 구덕팔");
        memberRepository.save(member2);
        log.info("saved {}", member2);

        log.info("read 팔덕구");
        member1 = memberRepository.findById(member1.getId()).orElseThrow();

        log.info("update 팔덕구");
        member1.setAge(27);
        memberRepository.save(member1);
        log.info("updated {}", member1);

        log.info("update 구덕팔");
        memberRepository.save(member2);
        log.info("updated {}", member2);

        log.info("애플리케이션 종료");

//        // 검색할 조건을 담은 예제 Member 객체를 생성
//        // 다음은 이름과 나이로 검색을 하며 값을 설정하지 않은 이메일은 검색 조건에서 제외한다.
//        Example<Member> example = Example.of(
//                Member.builder().name("팔덕구").age(28).build(),
//                ExampleMatcher.matchingAll());
//        // 예제 객체를 사용해 검색
//        List<Member> members = memberRepository.findAll(example);
//        log.info("{}", members);
//
//        // 검색할 조건을 담은 예제 Member 객체를 생성
//        // 다음은 이름과 나이로 OR 검색을 하며 값을 설정하지 않은 이메일은 검색 조건에서 제외한다.
//        example = Example.of(
//                Member.builder().name("팔덕구").age(28).build(),
//                ExampleMatcher.matchingAny());
//
//        // 예제 객체를 사용해 검색
//        members = memberRepository.findAll(example);
//        log.info("{}", members);
//
//        List<Object[]> memberStatsObjectList = memberRepository.getMemberStatsObject();
//        for (Object[] ob : memberStatsObjectList) {
//            String name = (String) ob[0];
//            String email = (String) ob[1];
//            Long count = (Long) ob[2];
//            log.info("{} {} {}", name, email, count);
//        }
//
//        List<MemberStats> memberStatsList = memberRepository.getMemberStats();
//        for (MemberStats memberStats : memberStatsList) {
//            log.info("{}", memberStats);
//        }
    }
}
