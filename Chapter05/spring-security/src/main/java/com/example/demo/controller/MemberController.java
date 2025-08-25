package com.example.demo.controller;

import com.example.demo.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class MemberController {
    private List<Member> members = List.of(
            new Member(1L, "구덕팔", "Duck89@naver.com", null),
            new Member(2L, "팔덕구", "Duck98@naver.com", null),
            new Member(3L, "김갑수", "GapsooKim@naver.com", null),
            new Member(4L, "수갑김", "GapkimSoo@naver.com", null)
    );

    @GetMapping("/member/list")
    public String getMembers(Model model) {
        model.addAttribute("members", members);
        return "/member-list";
    }
}
