package com.ssafy.goat.article.controller;

import com.ssafy.goat.article.controller.request.AddArticleRequest;
import com.ssafy.goat.article.dto.ArticleDto;
import com.ssafy.goat.article.service.ArticleService;
import com.ssafy.goat.member.dto.LoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/write")
    public String write(@SessionAttribute(name = "userinfo") LoginMember loginMember, Model model) {
        if (loginMember == null) {
            model.addAttribute("msg", "로그인 후 사용해주세요.");
            return "account/login";
        }
        return "addArticle";
    }

    @PostMapping("/write")
    public String write(@SessionAttribute(name = "userinfo") LoginMember loginMember, @Valid AddArticleRequest request, Model model) {
        if (loginMember == null) {
            model.addAttribute("msg", "로그인 후 사용해주세요.");
            return "account/login";
        }
        ArticleDto articleDto = ArticleDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        articleService.addArticle(loginMember.getId(), articleDto);
        return "redirect:/article/list";
    }

}
