package com.ssafy.goat.article.repository;

import article.dto.ArticleDetailDto;
import article.dto.ArticleListDto;
import article.dto.ArticleSearch;

import java.util.List;
import java.util.Optional;

public interface ArticleQueryRepository {

    Optional<ArticleDetailDto> findDetailById(Long articleId);

    List<ArticleListDto> findListByCondition(ArticleSearch condition, int pageNum, int amount);

    List<ArticleListDto> findListByMemberId(Long memberId, int pageNum, int amount);

    int findTotalCount();
}
