package com.example.demo.controller;

import com.example.demo.dto.MemberDto;
import com.example.demo.dto.MemberForm;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/list")
    public String getMemberList(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        Page<MemberDto> page = memberService.findAll(pageable);
        model.addAttribute("page", page);
        return "member-list";
    }

    @GetMapping("/edit")
    public String getMemberEdit(@RequestParam("id") Long id, @ModelAttribute("member") MemberForm memberForm) {
        MemberDto memberDto = memberService.findById(id);
        memberForm.setId(memberDto.getId());
        memberForm.setName(memberDto.getName());
        memberForm.setEmail(memberDto.getEmail());
        return "member-edit";
    }
}
