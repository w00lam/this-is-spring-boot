package com.example.demo.repository;

import com.example.demo.model.Member;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("회원 리포지터리 테스트")
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    // 테스트 시작 전 회원 리포지터리에 초기 데이터 생성
    @BeforeEach
    public void doBeforeEach() {
        memberRepository.save(Member.builder()
                .name("구덕팔").email("Duck89@naver.com")
                .age(10).enabled(true).build());
        memberRepository.save(Member.builder()
                .name("팔덕구").email("Duck98@naver.com")
                .age(26).enabled(true).build());
        memberRepository.save(Member.builder()
                .name("김갑수").email("GapsooKim@naver.com")
                .age(10).enabled(false).build());
        memberRepository.save(Member.builder()
                .name("팔갑수").email("GapsooPal@naver.com")
                .age(43).enabled(true).build());

    }

    // 각 테스트가 완료된 후에는 회원 레포지터리 삭제
    @AfterEach
    public void doAfterEach() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("조건 검색 테스트")
    public void testUserCase1() {
        // 회원 리포지터리에 저장된 개수가 4인지 검증
        assertThat(memberRepository.count()).isEqualTo(4);

        // '구덕팔'이라는 이름으로 검색된 결과 개수가 1인지 검증
        assertThat(memberRepository.findMemberByName("구덕팔").size()).isEqualTo(1);

        // 이름이 '구덕팔'이고 이메일이 'Duck98@naver.com'인 사용자를 조회한 결과 개수가 2인지 검증
        assertThat(memberRepository.findByNameOrEmail("구덕팔", "Duck98@naver.com").size()).isEqualTo(2);

        // 이름에 '팔'이라는 글자가 포함된 사용자를 조회한 결과 개수가 3인지 검증
        assertThat(memberRepository.findByNameContaining("팔").size()).isEqualTo(3);

        // 이름이 '구'로 끝나는 사람을 조회한 결과 개수가 1인지 검증
        assertThat(memberRepository.findByNameLike("%구").size()).isEqualTo(1);

        // 나이가 26세 초과인 사람의 수가 1인지 검증
        assertThat(memberRepository.findByAgeGreaterThan(26).size()).isEqualTo(1);

        // 나이가 26세 이상인 사람의 수가 2인지 검증
        assertThat(memberRepository.findByAgeGreaterThanEqual(26).size()).isEqualTo(2);

        // 나이가 26세 미만인 사람의 수가 2인지 검증
        assertThat(memberRepository.findByAgeLessThan(26).size()).isEqualTo(2);

        // 나이가 26세 이하인 사람의 수가 3인지 검증
        assertThat(memberRepository.findByAgeLessThanEqual(26).size()).isEqualTo(3);
    }

    @RepeatedTest(value = 3, name = "테스트 {displayName} 중 {currentRepetition} of {totalRepetitions}")
    @DisplayName("정렬 순서 테스트")
    public void testUserCase2() {
        assertThat(memberRepository.findAllByOrderByNameAsc().size()).isEqualTo(4);
        assertThat(memberRepository.findAllByOrderByNameAsc().getFirst().getName()).isEqualTo("구덕팔");
    }

    @Test
    @DisplayName("JPQL 테스트")
    @Disabled("잠시 테스트 중단")
    public void testUserCase3() {
        assertThat(memberRepository.findMemberByName("구덕팔").size()).isEqualTo(1);
        assertThat(memberRepository.findMemberByNameEmail("구덕팔", "Duck89@naver.com").size()).isEqualTo(1);
    }
}
