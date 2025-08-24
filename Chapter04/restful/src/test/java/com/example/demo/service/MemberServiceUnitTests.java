package com.example.demo.service;

import com.example.demo.dto.MemberResponse;
import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberServiceUnitTests {
    @MockitoBean
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    public void findById(){
        when(memberRepository.findById(1L))
                .thenReturn(Optional.ofNullable(Member.builder()
                        .id(1L)
                        .name("구덕팔")
                        .email("Duck89@naver.com")
                        .age(10).build()));

        MemberResponse response=memberService.findById(1L);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("구덕팔");
        assertThat(response.getEmail()).isEqualTo("Duck89@naver.com");
        assertThat(response.getAge()).isEqualTo(10);
    }
}
