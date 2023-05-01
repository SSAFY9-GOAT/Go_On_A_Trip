package com.ssafy.goat.member.controller;

import com.ssafy.goat.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final MemberService memberService;

    @GetMapping("/register")
    public String regist(){
        return "member/addMember";
    }

    @PostMapping("/register")
    public String regist(String a){
        return "";
    }
}
