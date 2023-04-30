package com.ssafy.goat.article.controller;

import com.ssafy.goat.article.controller.request.AddArticleRequest;
import com.ssafy.goat.article.dto.ArticleDetailDto;
import com.ssafy.goat.article.dto.ArticleDto;
import com.ssafy.goat.article.dto.ArticleListDto;
import com.ssafy.goat.article.dto.ArticleSearch;
import com.ssafy.goat.article.service.ArticleService;
import com.ssafy.goat.common.Page;
import com.ssafy.goat.common.validation.ArticleValidation;
import com.ssafy.goat.common.validation.dto.ArticleRequest;
import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.member.dto.LoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/list")
    public String list(HttpServletRequest request, Model model) {
        String condition = request.getParameter("condition") == null ? "" : request.getParameter("condition");
        int sortCondition = Integer.parseInt(request.getParameter("sortCondition") == null ? "1" : request.getParameter("sortCondition"));

        int pageNum = 1;
        int amount = 10;

        if (request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
            amount = Integer.parseInt(request.getParameter("amount"));
        }

        ArticleSearch articleSearch = ArticleSearch.builder()
                .condition(condition)
                .sortCondition(sortCondition)
                .build();

        List<ArticleListDto> articles = articleService.searchArticles(articleSearch, pageNum, amount);
        int totalCount = articleService.getTotalCount();
        Page page = new Page(pageNum, amount, totalCount);

        model.addAttribute("page", page);
        model.addAttribute("articles", articles);
        return "/article/articleList";
    }

    @GetMapping("/detail/{articleId}")
    public String detail(@SessionAttribute(name = "userinfo") LoginMember loginMember, @PathVariable Long articleId, Model model) {
        if (loginMember == null) {
            model.addAttribute("msg", "로그인 후 사용해주세요.");
            return "account/login";
        }

        articleService.increaseHit(articleId);
        ArticleDetailDto articleDetailDto = articleService.searchArticle(articleId);
        model.addAttribute("article", articleDetailDto);
        model.addAttribute("isMine", articleDetailDto.getMemberId().equals(loginMember.getId()));
        return "/article/viewArticle";
    }

    @GetMapping("/edit")
    public String edit(@SessionAttribute(name = "userinfo") LoginMember loginMember, Model model) {
        if (loginMember == null) {
            model.addAttribute("msg", "로그인 후 사용해주세요.");
            return "account/login";
        }
        return "article/editArticle";
    }

    @PostMapping("/edit/{articleId}")
    public String edit(@SessionAttribute(name = "userinfo") LoginMember loginMember, @PathVariable Long articleId, Model model) {

        ArticleDetailDto article = (ArticleDetailDto) model.getAttribute("article");
        String title = article.getTitle();
        String content = article.getContent();

        ArticleValidation articleValidation = new ArticleValidation();

        ArticleRequest articleRequest = ArticleRequest.builder()
                .title(title)
                .content(content)
                .build();

        List<InvalidResponse> validate = articleValidation.validate(articleRequest);

        ArticleDto articleDto = ArticleDto.builder()
                .title(title)
                .content(content)
                .build();

        articleService.editArticle(articleId, loginMember.getId(), articleDto);

        return "redirect:/article/detail/" + articleId;
    }

    @GetMapping("/{articleId}/remove")
    public String remove(@PathVariable Long articleId, @SessionAttribute(name = "userinfo") LoginMember loginMember, Model model) {
        if (loginMember == null) {
            model.addAttribute("msg", "로그인 후 사용해주세요.");
            return "account/login";
        }

        articleService.removeArticle(articleId, loginMember.getId());
        return "redirect:/article/list";
    }
}
