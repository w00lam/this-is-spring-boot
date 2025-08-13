package com.example.demo;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JpaApplication implements ApplicationRunner {
    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        memberRepository.save(Member.builder()
                .name("팔덕구")
                .email("duck98@naver.com")
                .age(28).build());
        memberRepository.save(Member.builder()
                .name("구덕팔")
                .email("duck89@naver.com")
                .age(26).build());
    }
}
