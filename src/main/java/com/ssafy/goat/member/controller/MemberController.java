package com.ssafy.goat.member.controller;

import com.ssafy.goat.article.dto.ArticleListDto;
import com.ssafy.goat.article.service.ArticleService;
import com.ssafy.goat.common.Page;
import com.ssafy.goat.hotplace.dto.HotPlaceListDto;
import com.ssafy.goat.hotplace.service.HotPlaceService;
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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final HotPlaceService hotPlaceService;
    private final ArticleService articleService;

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

    @GetMapping("/modifyemail")
    public String modifyEmail(Model model, HttpSession session) {
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        MemberDto dto = memberService.myPage(loginMember.getId());
        model.addAttribute("currShow", "modifyEmail");
        model.addAttribute("loginUserDto", dto);
        return "member/mypage";
    }

    @PostMapping("/modifyemail")
    public String modifyEmail(@RequestParam("currNickname") String currNickname, @RequestParam("newNickname") String newNickname, @RequestParam("pwCheck") String pwCheck, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");

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

    @GetMapping("/withdrawal")
    public String withdrawal(HttpSession session) {
        session.setAttribute("currShow", "deleteMember");
        return "member/mypage";
    }

    @PostMapping("/withdrawal")
    public String withdrawal(HttpSession session, Model model) {
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        if (loginMember == null) {
            return "redirect:/";
        }
        String loginPw = (String) model.getAttribute("pw");
        memberService.withdrawal(loginMember.getId(), loginPw);
        return "redirect:/logout";
    }

    @GetMapping("/myFavorite")
    public String myFavorite(
            @SessionAttribute(name = "userinfo") LoginMember loginMember,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int amount,
            Model model,
            HttpSession session) {

        Long memberId = loginMember.getId();

        List<HotPlaceListDto> favorites = hotPlaceService.searchFavorites(memberId, pageNum, amount);
        int totalCount = favorites.size();
        Page page = new Page(pageNum, amount, totalCount);

        model.addAttribute("page", page);
        model.addAttribute("favorites", favorites);

        session.setAttribute("currShow", "favorite");
        return "member/mypage/myFavorite";
    }

    @GetMapping("/likeHotPlace")
    public String myFavorite(
            @SessionAttribute(name = "userinfo") LoginMember loginMember,
            @RequestParam Long hotPlaceId,
            HttpSession session) {
        log.debug("hot 좋아요 아이디" + hotPlaceId);

        Long memberId = loginMember.getId();
        hotPlaceService.doFavorite(memberId, hotPlaceId); //insert

        return "redirect:/myFavorite";
    }

    @GetMapping("/myHotPlace")
    public String myHotPlace(
            @SessionAttribute(name = "userinfo") LoginMember loginMember,
            Model model) {
        List<HotPlaceListDto> hotPlaces = hotPlaceService.searchHotPlaces(loginMember.getId());
        model.addAttribute("hotPlaces", hotPlaces);
        return "member/mypage/myHotplace";
    }

    @GetMapping("/myArticle")
    public String myArticle(
            @SessionAttribute(name = "userinfo") LoginMember loginMember,
            HttpSession session,
            Model model) {

        int pageNum = 1;
        int amount = 10;

        Long memberId = loginMember.getId();

        List<ArticleListDto> articles = articleService.searchMyArticles(memberId, pageNum, amount);
        int totalCount = articleService.getTotalCount();
        Page page = new Page(pageNum, amount, totalCount);

        model.addAttribute("page", page);
        model.addAttribute("articles", articles);

        session.setAttribute("currShow", "myArticle");
        return "member/mypage/myArticle";
    }
}
