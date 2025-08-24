package com.example.demo.controller;

import com.example.demo.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class ExpressionController {
    private final List<Member> members = List.of(
            new Member("구덕팔", "Duck89@naver.com", 10),
            new Member("팔덕구", "Duck98@naver.com", 43),
            new Member("김갑수", "GapsooKim@naver.com", 28),
            new Member("수갑김", "GapkimSoo@naver.com", 10)
    );

    @GetMapping("/object")
    public String getMember(Model model) {
        model.addAttribute("member", members.getFirst());
        return "expression/object";
    }

    @GetMapping("/calendars")
    public String getCalendars(Model model) {
        Date date = Calendar.getInstance().getTime();
        model.addAttribute("date", date);
        return "expression/calendar";
    }

    @GetMapping("/numbers")
    public String getNumbers(Model model) {
        model.addAttribute("productPrice", 345620.5226);
        model.addAttribute("productCount", 3502340);
        return "expression/numbers";
    }

    @GetMapping("/condition")
    public String getCondition(Model model) {
        model.addAttribute("showWelcome", true);
        model.addAttribute("showDescription", false);
        return "expression/condition";
    }

    @GetMapping("/loop")
    public String getMemberList(Model model) {
        model.addAttribute("members", members);
        return "expression/loop";
    }

    @GetMapping("/link")
    public String getLink(Model model) {
        model.addAttribute("id", 3);
        return "expression/link";
    }

    @GetMapping("/member")
    public String getMemberByIdParam(@RequestParam(name = "id", required = false) Integer id, Model model) {
        if (id != null) {
            model.addAttribute("member", members.get(id));
        } else {
            model.addAttribute("member", members.getFirst());
        }
        return "expression/member";
    }

    @GetMapping("/member/{id}")
    public String getMemberByIdPath(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("member", members.get(id));
        return "expression/member";
    }
}
