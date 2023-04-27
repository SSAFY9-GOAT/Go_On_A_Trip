package com.ssafy.goat.article.service;

import com.ssafy.goat.article.Article;
import com.ssafy.goat.article.dto.ArticleDto;
import com.ssafy.goat.article.repository.ArticleJdbcRepository;
import com.ssafy.goat.article.repository.ArticleRepository;
import com.ssafy.goat.common.exception.ArticleException;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.repository.MemberJdbcRepository;
import com.ssafy.goat.member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.ssafy.goat.common.exception.ExceptionMessage.*;
import static com.ssafy.goat.member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleServiceTest {

    private final ArticleService articleService = ArticleServiceImpl.getArticleService();

    private final ArticleRepository articleRepository = ArticleJdbcRepository.getArticleRepository();
    private final MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();

    private Long memberId;
    private Long articleId;

    @BeforeEach
    void beforeEach() {
        Member member = Member.builder()
                .loginId("ssafy")
                .loginPw("12345678")
                .username("김싸피")
                .email("ssafy@ssafy.com")
                .phone("01012345678")
                .birth("010101")
                .gender("1")
                .nickname("광주5반")
                .authority(CLIENT)
                .build();
        memberRepository.save(member);
        Member findMember = memberRepository.findByLoginId("ssafy").get();
        memberId = findMember.getId();
        Article article = Article.builder()
                .title("beforeEach title")
                .content("beforeEach content")
                .member(findMember)
                .build();
        articleRepository.save(article);
        articleId = articleRepository.findAll().get(0).getId();
    }

    @AfterEach
    void afterEach() {
        articleRepository.clear();
        memberRepository.clear();
    }

    @Test
    @DisplayName("게시물 등록")
    void addArticle() {
        //given
        ArticleDto articleDto = ArticleDto.builder().title("beforeEach title").content("beforeEach content").build();

        //when
        int count = articleService.addArticle(memberId, articleDto);

        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("게시물 등록#미등록 회원 예외")
    void exception_member() {
        //given
        ArticleDto articleDto = ArticleDto.builder().title("beforeEach title").content("beforeEach content").build();

        //when
        //then
        assertThatThrownBy(() -> articleService.addArticle(0L, articleDto))
                .isInstanceOf(ArticleException.class)
                .hasMessageContaining(ARTICLE_MEMBER_DISCREPANCY);
    }

    @Test
    @DisplayName("게시물 수정")
    void editArticle() {
        //given
        ArticleDto articleDto = ArticleDto.builder().title("new title").content("new content").build();

        //when
        int count = articleService.editArticle(articleId, memberId, articleDto);

        //then
        Article findArticle = articleRepository.findById(articleId).get();
        assertThat(findArticle.getTitle()).isEqualTo("new title");
    }

    @Test
    @DisplayName("게시물 수정#수정 권한이 없는 회원")
    void editArticle_exception_member() {
        //given
        ArticleDto articleDto = ArticleDto.builder().title("beforeEach title").content("beforeEach content").build();

        //when
        //then
        assertThatThrownBy(() -> articleService.editArticle(articleId, 0L, articleDto))
                .isInstanceOf(ArticleException.class)
                .hasMessageContaining(ARTICLE_MEMBER_DISCREPANCY);
    }

    @Test
    @DisplayName("게시물 수정#등록되지 않은 게시물")
    void editArticle_exception_article() {
        //given
        ArticleDto articleDto = ArticleDto.builder().title("beforeEach title").content("beforeEach content").build();

        //when
        //then
        assertThatThrownBy(() -> articleService.editArticle(0L, memberId, articleDto))
                .isInstanceOf(ArticleException.class)
                .hasMessageContaining(NOT_FOUND_ARTICLE);
    }

    @Test
    @DisplayName("게시물 삭제")
    void removeArticle() {
        //given
        //when
        articleService.removeArticle(articleId, memberId);

        //then
        Optional<Article> findArticle = articleRepository.findById(articleId);
        assertThat(findArticle).isEmpty();
    }

    @Test
    @DisplayName("게시물 삭제#등록되지 않은 게시물")
    void removeArticle_exception_article() {
        //given
        //when
        //then
        assertThatThrownBy(() -> articleService.removeArticle(0L, memberId))
                .isInstanceOf(ArticleException.class)
                .hasMessageContaining(NOT_FOUND_ARTICLE);
    }

    @Test
    @DisplayName("게시물 삭제#미등록 회원")
    void removeArticle_exception_member() {
        //given
        //when
        //then
        assertThatThrownBy(() -> articleService.removeArticle(articleId, 0L))
                .isInstanceOf(ArticleException.class)
                .hasMessageContaining(ARTICLE_MEMBER_DISCREPANCY);
    }
}