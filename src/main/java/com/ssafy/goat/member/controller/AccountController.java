package com.ssafy.goat.member.controller;

import com.ssafy.goat.member.dto.MemberAddDto;
import com.ssafy.goat.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final MemberService memberService;

    @GetMapping("/register")
    public String regist(){
        return "member/addMember";
    }

    @PostMapping("/register")
    public String regist(MemberAddDto memberAddDto){
        memberAddDto.setGender(memberAddDto.getGender().substring(0,1));
        memberService.signUp(memberAddDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "account/login";
    }
}
