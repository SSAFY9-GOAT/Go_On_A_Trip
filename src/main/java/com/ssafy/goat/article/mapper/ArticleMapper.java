package com.ssafy.goat.article.mapper;

import com.ssafy.goat.article.Article;
import com.ssafy.goat.article.dto.ArticleDetailDto;
import com.ssafy.goat.article.dto.ArticleListDto;
import com.ssafy.goat.article.dto.ArticleSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArticleMapper {
    int save(Article article);

    Optional<Article> findById(Long articleId);

    List<Article> findAll();

    int update(Article article);

    int updateHit(Article article);

    int remove(Long articleId);

    void clear();

    Optional<ArticleDetailDto> findDetailById(Long articleId);

    List<ArticleListDto> findListByCondition(ArticleSearch condition, int pageNum, int amount);

    List<ArticleListDto> findListByMemberId(Long memberId, int pageNum, int amount);

    int findTotalCount();
}
