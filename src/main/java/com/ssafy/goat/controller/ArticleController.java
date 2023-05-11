package com.ssafy.goat.controller;

import com.ssafy.goat.article.controller.request.AddArticleRequest;
import com.ssafy.goat.article.dto.ArticleDetailDto;
import com.ssafy.goat.article.dto.ArticleDto;
import com.ssafy.goat.article.dto.ArticleListDto;
import com.ssafy.goat.article.dto.ArticleSearch;
import com.ssafy.goat.article.service.ArticleService;
import com.ssafy.goat.common.Page;
import com.ssafy.goat.member.dto.LoginMember;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/write")
    @ApiOperation(value = "게시글을 작성")
    public ResponseEntity<?> write(
            @RequestBody AddArticleRequest request,
            @SessionAttribute(name = "userinfo") LoginMember loginMember
    ) {
        ArticleDto articleDto = ArticleDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        long result = articleService.addArticle(loginMember.getId(), articleDto);
        return new ResponseEntity<Long>(result, HttpStatus.CREATED);
    }

    @GetMapping("")
    @ApiOperation(value = "게시글 목록을 불러옴", response = ArticleListDto.class)
    public ResultPage<?> selectAll(
            @RequestParam("condition") String condition,
            @RequestParam("sortCondition") int sortCondition,
            @RequestParam("pageNum") Integer pn,
            @RequestParam("amount") Integer am
    ) {
        int pageNum = 1;
        int amount = 10;
        if (pn != null && am != null) {
            pageNum = pn;
            amount = am;
        }

        ArticleSearch articleSearch = ArticleSearch.builder()
                .condition(condition)
                .sortCondition(sortCondition)
                .build();
        int totalCount = articleService.getTotalCount();
        Page page = new Page(pageNum, amount, totalCount);
        List<ArticleListDto> articles = articleService.searchArticles(articleSearch, (pageNum - 1) * amount, amount);
        if (articles != null && articles.size() > 0) {
            return new ResultPage<List<ArticleListDto>>(articles, page, HttpStatus.OK);
        } else {
            return new ResultPage<Void>(null, null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{articleId}")
    @ApiOperation(value = "{articleId}에 해당하는 게시글 정보를 반환", response = ArticleDetailDto.class)
    public ResultArticle<?> detail(
            @PathVariable Long articleId,
            @SessionAttribute(name = "userinfo") LoginMember loginMember
    ){
        ArticleDetailDto articleDetailDto = articleService.searchArticle(articleId);
        articleService.increaseHit(articleId);
        return new ResultArticle<ArticleDetailDto>(
                articleDetailDto,
                articleDetailDto.getMemberId().equals(loginMember.getId()),
                HttpStatus.OK
                );
    }

    @PostMapping("/{articleId}/edit")
    @ApiOperation(value = "{articleId}에 해당하는 게시글 정보를 수정")
    public ResponseEntity<?> edit(
            @PathVariable Long articleId,
            @SessionAttribute(name = "userinfo") LoginMember loginMember){
        ArticleDetailDto articleDetailDto = articleService.searchArticle(articleId);
        String title = articleDetailDto.getTitle();
        String content = articleDetailDto.getContent();

        ArticleDto articleDto = ArticleDto.builder()
                .title(title)
                .content(content)
                .build();

        long result = articleService.editArticle(articleId, loginMember.getId(), articleDto);
        return new ResponseEntity<Long>(result, HttpStatus.OK);
    }

    @PostMapping("/{articleId}/remove")
    @ApiOperation(value = "{articleId}에 해당하는 게시글을 삭제")
    public ResponseEntity<?> delete(
            @PathVariable Long articleId,
            @SessionAttribute(name = "userinfo") LoginMember loginMember){
        ArticleDetailDto articleDetailDto = articleService.searchArticle(articleId);
        if(!articleDetailDto.getMemberId().equals(loginMember.getId())){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
        long result = articleService.removeArticle(articleId,loginMember.getId());
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    static class ResultArticle<T> {
        private T data;
        private boolean isMine;
        private HttpStatus status;
    }

    @Data
    @AllArgsConstructor
    static class ResultPage<T> {
        private T data;
        private Page page;
        private HttpStatus status;
    }
}
