package com.example.demo.controller;

import com.example.demo.model.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {
    private List<Member> members = List.of(
            new Member(1L, "구덕팔", "Duck89@naver.com", null),
            new Member(2L, "팔덕구", "Duck98@naver.com", null),
            new Member(3L, "김갑수", "GapsooKim@naver.com", null),
            new Member(4L, "수갑김", "GapkimSoo@naver.com", null)
    );

    @GetMapping("/api/members")
    public List<Member> getMembers() {
        return members;
    }
}
