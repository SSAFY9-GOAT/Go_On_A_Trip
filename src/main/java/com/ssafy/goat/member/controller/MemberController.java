package com.ssafy.goat.member.controller;

import com.ssafy.goat.member.dto.LoginMember;
import com.ssafy.goat.member.dto.MemberDto;
import com.ssafy.goat.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/mypage")
    public String myPage(HttpSession session, Model model) {
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

        model.addAttribute("currShow", "myPage");
        model.addAttribute("loginUserDto", dto);
        return "member/mypage";
    }

    @GetMapping("/modifypw")
    public String modifyPw(Model model) {
        model.addAttribute("currShow", "modifyPw");
        return "member/mypage";
    }

    @PostMapping("/modifypw")
    public String modifyPw(@RequestParam("currPw") String currPw, @RequestParam("newPw") String newPw, @RequestParam("newPwCheck") String newPwCheck, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");

        if (!currPw.equals(loginMember.getLoginPw())) {
            model.addAttribute("msg", "비밀번호가 틀렸습니다.");
            return "member/mypage";
        }
        if (!newPw.equals(newPwCheck)) {
            model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
            return "member/mypage";
        }
        if (currPw.equals(newPw)) {
            model.addAttribute("msg", "기존 비밀번호와 같습니다.");
            return "member/mypage";
        }

        memberService.changePassword(loginMember.getId(), newPw);
        redirectAttributes.addFlashAttribute("msg", "비밀번호 변경이 완료되었습니다. 다시 로그인 하세요.");
//        model.addAttribute("msg", "비밀번호 변경이 완료되었습니다. 다시 로그인 하세요.");
        return "redirect:/logout";
    }
}
