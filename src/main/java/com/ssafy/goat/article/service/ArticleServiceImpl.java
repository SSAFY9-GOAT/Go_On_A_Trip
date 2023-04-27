package com.ssafy.goat.article.service;

import article.Article;
import article.dto.ArticleDetailDto;
import article.dto.ArticleDto;
import article.dto.ArticleListDto;
import article.dto.ArticleSearch;
import article.repository.ArticleJdbcRepository;
import article.repository.ArticleQueryJdbcRepository;
import article.repository.ArticleQueryRepository;
import article.repository.ArticleRepository;
import common.exception.ArticleException;
import member.Authority;
import member.Member;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

import static common.exception.ExceptionMessage.*;

public class ArticleServiceImpl implements ArticleService {

    private static final ArticleService articleService = new ArticleServiceImpl();
    private final ArticleRepository articleRepository;
    private final ArticleQueryRepository articleQueryRepository;
    private final MemberRepository memberRepository;

    public ArticleServiceImpl() {
        articleRepository = ArticleJdbcRepository.getArticleRepository();
        articleQueryRepository = ArticleQueryJdbcRepository.getArticleQueryRepository();
        memberRepository = MemberJdbcRepository.getMemberRepository();
    }

    public static ArticleService getArticleService() {
        return articleService;
    }

    @Override
    public int addArticle(Long memberId, ArticleDto articleDto) {
        Member member = findMember(memberId);

        Article article = Article.builder()
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .member(member)
                .build();

        return articleRepository.save(article);
    }

    @Override
    public ArticleDetailDto searchArticle(Long articleId) {
        Optional<ArticleDetailDto> findArticle = articleQueryRepository.findDetailById(articleId);
        if (!findArticle.isPresent()) {
            throw new ArticleException(ARTICLE_EXCEPTION);
        }
        return findArticle.get();
    }

    @Override
    public List<ArticleListDto> searchArticles(ArticleSearch condition, int pageNum, int amount) {
        return articleQueryRepository.findListByCondition(condition, pageNum, amount);
    }

    @Override
    public List<ArticleListDto> searchMyArticles(Long memberId, int pageNum, int amount) {
        return articleQueryRepository.findListByMemberId(memberId, pageNum, amount);
    }

    @Override
    public int getTotalCount() {
        return articleQueryRepository.findTotalCount();
    }

    @Override
    public int editArticle(Long articleId, Long memberId, ArticleDto articleDto) {
        Article article = findArticle(articleId);
        if (!article.getMember().getId().equals(memberId)) {
            throw new ArticleException(ARTICLE_MEMBER_DISCREPANCY);
        }

        article.editArticle(articleDto.getTitle(), articleDto.getContent());

        return articleRepository.update(article);
    }

    @Override
    public int increaseHit(Long articleId) {
        Article article = findArticle(articleId);
        article.increaseHit();

        return articleRepository.updateHit(article);
    }

    @Override
    public int removeArticle(Long articleId, Long memberId) {
        Article article = findArticle(articleId);
        Member member = findMember(memberId);

        if (isNotMine(article, memberId) && isNotAuthority(member)) {
            throw new ArticleException(ARTICLE_MEMBER_DISCREPANCY);
        }

        return articleRepository.remove(articleId);
    }

    private Member findMember(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new ArticleException(ARTICLE_MEMBER_DISCREPANCY);
        }
        return findMember.get();
    }

    private Article findArticle(Long articleId) {
        Optional<Article> findArticle = articleRepository.findById(articleId);
        if (!findArticle.isPresent()) {
            throw new ArticleException(NOT_FOUND_ARTICLE);
        }
        return findArticle.get();
    }

    private boolean isNotMine(Article article, Long memberId) {
        return !article.getMember().getId().equals(memberId);
    }

    private boolean isNotAuthority(Member member) {
        return member.getAuthority() == Authority.CLIENT;
    }
}
