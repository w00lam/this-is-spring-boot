package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SpringJdbcApplication implements ApplicationRunner {
    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // create
        Member member = Member.builder()
                .name("정혁")
                .email("HyeokJung@hanbit.co.kr")
                .age(10).build();
        memberRepository.save(member);

        memberRepository.save(Member.builder()
                .name("팔덕구")
                .email("duck98@naver.com")
                .age(28).build());
        memberRepository.save(Member.builder()
                .name("구덕팔")
                .email("9duck8@naver.com")
                .age(26).build());

        // update
        member.setAge(11);
        memberRepository.save(member);

        // find all members
        var members = memberRepository.findAll();
        log.info("{}", members);

        // find member by id
        var member1 = memberRepository.findById(1L);
        log.info("{}", member);
    }
}
