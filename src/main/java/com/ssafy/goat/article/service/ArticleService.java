package com.ssafy.goat.article.service;



import com.ssafy.goat.article.dto.ArticleDetailDto;
import com.ssafy.goat.article.dto.ArticleDto;
import com.ssafy.goat.article.dto.ArticleListDto;
import com.ssafy.goat.article.dto.ArticleSearch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ArticleService {


    int addArticle(Long memberId, ArticleDto articleDto);

    @Transactional(readOnly = true)
    ArticleDetailDto searchArticle(Long articleId);

    @Transactional(readOnly = true)
    List<ArticleListDto> searchArticles(ArticleSearch condition, int pageNum, int amount);

    @Transactional(readOnly = true)
    List<ArticleListDto> searchMyArticles(Long memberId, int pageNum, int amount);

    @Transactional(readOnly = true)
    int getTotalCount();

    int editArticle(Long articleId, Long memberId, ArticleDto articleDto);

    int increaseHit(Long articleId);

    int removeArticle(Long articleId, Long memberId);
}
