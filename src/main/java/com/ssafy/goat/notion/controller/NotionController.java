package com.ssafy.goat.notion.controller;

import com.ssafy.goat.article.controller.request.AddArticleRequest;
import com.ssafy.goat.common.Page;
import com.ssafy.goat.member.dto.LoginMember;
import com.ssafy.goat.notion.controller.request.AddNotionRequest;
import com.ssafy.goat.notion.dto.NotionDto;
import com.ssafy.goat.notion.service.NotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/notion")
@RequiredArgsConstructor
public class NotionController {

    private final NotionService notionService;

    @GetMapping("/list")
    public String list(HttpServletRequest request, Model model){
        int pageNum = 1;
        int amount = 10;

        if (request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
            amount = Integer.parseInt(request.getParameter("amount"));
        }

        List<NotionDto> topNotions = notionService.searchTopNotions();
        List<NotionDto> notions = notionService.searchNotions((pageNum-1)*amount, amount);
        int totalCount = notionService.getTotalCount();
        Page page = new Page(pageNum, amount, totalCount);

        model.addAttribute("page", page);
        model.addAttribute("topNotions", topNotions);
        model.addAttribute("notions", notions);

        return "notion/notionList";
    }

    @GetMapping("/write")
    public String write(@SessionAttribute(name = "userinfo") LoginMember loginMember,Model model){
        if (loginMember == null) {
            model.addAttribute("msg", "로그인 후 사용해주세요.");
            return "account/login";
        }
        return "notion/addNotion";
    }

    @PostMapping("/write")
    public String write(@SessionAttribute(name = "userinfo") LoginMember loginMember, @Valid AddNotionRequest request, Model model){
        if (loginMember == null) {
            model.addAttribute("msg", "로그인 후 사용해주세요.");
            return "account/login";
        }
        NotionDto notionDto = NotionDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        notionService.addNotion(loginMember.getId(), notionDto);
        return "redirect:/notion/list";
    }

    @GetMapping("/{notionId}")
    public String view(@PathVariable Long notionId, Model model){
        NotionDto notion = notionService.searchNotion(notionId);
        model.addAttribute("notion", notion);
        return "notion/viewNotion";
    }

    @GetMapping("/{notionId}/modify")
    public String modify(@SessionAttribute(name = "userinfo") LoginMember loginMember, @PathVariable Long notionId, Model model){
        if (loginMember == null) {
            model.addAttribute("msg", "로그인 후 사용해주세요.");
            return "account/login";
        }
        NotionDto notion = notionService.searchNotion(notionId);
        model.addAttribute("notion", notion);
        return "notion/editNotion";
    }

    @PostMapping("/{notionId}/modify")
    public String modify(@Valid AddNotionRequest request,@SessionAttribute(name = "userinfo") LoginMember loginMember, @PathVariable Long notionId, Model model){
        if (loginMember == null) {
            model.addAttribute("msg", "로그인 후 사용해주세요.");
            return "account/login";
        }

        String title = request.getTitle();
        String content = request.getContent();

        NotionDto notionDto = NotionDto.builder()
                .id(notionId)
                .title(title)
                .content(content)
                .build();

        notionService.editNotion(notionId, loginMember.getId(), notionDto);
        return "redirect:/notion/"+notionId;
    }

    @GetMapping("/{notionId}/remove")
    public String remove(@SessionAttribute(name = "userinfo") LoginMember loginMember, @PathVariable Long notionId, Model model){
        if (loginMember == null) {
            model.addAttribute("msg", "로그인 후 사용해주세요.");
            return "account/login";
        }

        notionService.removeNotion(notionId, loginMember.getId());
        return "redirect:/notion/list";
    }
}
