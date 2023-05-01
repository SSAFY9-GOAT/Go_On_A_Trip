package com.ssafy.goat.member.controller;

import com.ssafy.goat.member.dto.LoginMember;
import com.ssafy.goat.member.dto.MemberDto;
import com.ssafy.goat.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
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
            model.addAttribute("currShow", "modifyPw");
            return "member/mypage";
        }
        if (!newPw.equals(newPwCheck)) {
            model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
            model.addAttribute("currShow", "modifyPw");
            return "member/mypage";
        }
        if (currPw.equals(newPw)) {
            model.addAttribute("msg", "기존 비밀번호와 같습니다.");
            model.addAttribute("currShow", "modifyPw");
            return "member/mypage";
        }

        memberService.changePassword(loginMember.getId(), newPw);
        redirectAttributes.addFlashAttribute("msg", "비밀번호 변경이 완료되었습니다. 다시 로그인 하세요.");
//        model.addAttribute("msg", "비밀번호 변경이 완료되었습니다. 다시 로그인 하세요.");
        return "redirect:/logout";
    }

    @GetMapping("/modifynickname")
    public String modifyNickname(Model model, HttpSession session) {
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        MemberDto dto = memberService.myPage(loginMember.getId());
        model.addAttribute("currShow", "modifyNickname");
        model.addAttribute("loginUserDto", dto);
        return "member/mypage";
    }

    @PostMapping("/modifynickname")
    public String modifyNickname(@RequestParam("currNickname") String currNickname, @RequestParam("newNickname") String newNickname, @RequestParam("pwCheck") String pwCheck, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");

//        log.debug("currPw = "+currPw);
//        log.debug("currPw = "+currPw);
//        log.debug("currPw = "+currPw);

        if (!pwCheck.equals(loginMember.getLoginPw())) {
            model.addAttribute("msg", "비밀번호가 틀렸습니다.");
            model.addAttribute("currShow", "modifyNickname");
            model.addAttribute("currNickname", currNickname);
            return "member/mypage";
        }
        if (currNickname.equals(newNickname)) {
            model.addAttribute("msg", "기존 닉네임과 같습니다.");
            model.addAttribute("currShow", "modifyNickname");
            model.addAttribute("currNickname", currNickname);
            return "member/mypage";
        }

        memberService.changeNickname(loginMember.getId(), newNickname);
        redirectAttributes.addFlashAttribute("msg", "닉네임 변경이 완료되었습니다. ");
        model.addAttribute("currShow", "modifyNickname");
//        model.addAttribute("msg", "비밀번호 변경이 완료되었습니다. 다시 로그인 하세요.");
        return "member/mypage";
    }
}
