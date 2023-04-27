package com.ssafy.goat.article.service;

import article.dto.ArticleDetailDto;
import article.dto.ArticleDto;
import article.dto.ArticleListDto;
import article.dto.ArticleSearch;

import java.util.List;

public interface ArticleService {

    int addArticle(Long memberId, ArticleDto articleDto);

    ArticleDetailDto searchArticle(Long articleId);

    List<ArticleListDto> searchArticles(ArticleSearch condition, int pageNum, int amount);

    List<ArticleListDto> searchMyArticles(Long memberId, int pageNum, int amount);

    int getTotalCount();

    int editArticle(Long articleId, Long memberId, ArticleDto articleDto);

    int increaseHit(Long articleId);

    int removeArticle(Long articleId, Long memberId);
}
