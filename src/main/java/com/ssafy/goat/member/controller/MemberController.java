package com.ssafy.goat.member.controller;

import com.ssafy.goat.member.dto.LoginMember;
import com.ssafy.goat.member.dto.MemberDto;
import com.ssafy.goat.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/mypage")
    public String myPage(HttpSession session, Model model){
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        MemberDto dto = memberService.myPage(loginMember.getId());
        String birth1 = dto.getBirth().substring(0, 2);
        String birth2 = dto.getBirth().substring(2, 4);
        String birth3 = dto.getBirth().substring(4, 6);
        if (Integer.parseInt(dto.getGender()) > 2) {
            dto.setBirth("20" + birth1 + "년 " + birth2 + "월 " + birth3 + "일");
        } else {
            dto.setBirth("19" + birth1 + "년 " + birth2 + "월 " + birth3 + "일");
        }

        if (Integer.parseInt(dto.getGender()) % 2 == 0) {
            dto.setGender("여성");
        } else {
            dto.setGender("남성");
        }

        model.addAttribute("currShow","myPage");
        model.addAttribute("loginUserDto", dto);
        return  "member/mypage";
    }
}
