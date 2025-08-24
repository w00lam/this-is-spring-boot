package com.example.demo.service;

import com.example.demo.dto.MemberRequest;
import com.example.demo.dto.MemberResponse;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberServiceTests {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @AfterEach
    public void doAfterEach() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원 추가 및 조회")
    public void testUsers() {
        // '구덕팔' 회원을 추가하고 아이디가 자동으로 생성되었는지를 검증한다.
        MemberRequest userRequest = MemberRequest.builder().name("구덕팔").age(10).build();
        MemberResponse userResponse = memberService.create(userRequest);
        assertThat(userResponse.getId()).isNotNull();

        // '팔덕구' 회원을 추가하고 아이디가 자동으로 생성되었는지를 검증한다.
        userRequest = MemberRequest.builder().name("팔덕구").age(43).build();
        userResponse = memberService.create(userRequest);
        assertThat(userResponse.getId()).isNotNull();

        // 회원을 모두 조회해 두 명이 조회되는지를 검증한다.
        List<MemberResponse> results = memberService.findAll();
        assertThat(results.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("트랜잭션 커밋 테스트")
    public void testTransactionalCommit() {
        // 모두 네 명의 회원을 추가하며 데이터에 오류가 없기 때문에
        // 트랜잭션에서 4개의 입력 모두 커밋되어야 한다.
        List<MemberRequest> userRequests = List.of(
                MemberRequest.builder().name("구덕팔")
                        .email("Duck89@naver.com").age(10).build(),
                MemberRequest.builder().name("팔덕구")
                        .email("Duck98@naver.com").age(43).build(),
                MemberRequest.builder().name("김갑수")
                        .email("GapsooKim@naver.com").age(11).build(),
                MemberRequest.builder().name("수갑김")
                        .email("GapkimSoo@naver.com").age(28).build()
        );
        try {
            memberService.createBatch(userRequests);
        } catch (Exception ignored) {
            assertThat(memberRepository.count()).isEqualTo(4);
        }
    }

    @Test
    @DisplayName("트랜잭션 롤백 테스트")
    public void testTransactionalRollback() {
        // 모두 네 명의 회원을 추가하지만 중복된 이메일이 있기 때문에
        // 트랜잭션에서 모두 롤백되어야 한다.
        List<MemberRequest> userRequests = List.of(
                MemberRequest.builder().name("구덕팔")
                        .email("Duck89@naver.com").age(10).build(),
                MemberRequest.builder().name("팔덕구")
                        .email("Duck98@naver.com").age(43).build(),
                MemberRequest.builder().name("김갑수")
                        .email("Duck89@naver.com").age(11).build(),
                MemberRequest.builder().name("수갑김")
                        .email("GapkimSoo@naver.com").age(28).build()
        );
        try {
            memberService.createBatch(userRequests);
        } catch (Exception ignored) {
            assertThat(memberRepository.count()).isEqualTo(0);
        }
    }
}
