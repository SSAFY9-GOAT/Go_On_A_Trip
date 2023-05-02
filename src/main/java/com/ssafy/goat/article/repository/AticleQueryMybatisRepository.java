package com.ssafy.goat.article.repository;

import com.ssafy.goat.article.dto.ArticleDetailDto;
import com.ssafy.goat.article.dto.ArticleListDto;
import com.ssafy.goat.article.dto.ArticleSearch;
import com.ssafy.goat.article.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AticleQueryMybatisRepository implements ArticleQueryRepository{
    private final ArticleMapper articleMapper;

    @Override
    public Optional<ArticleDetailDto> findDetailById(Long articleId) {
        return articleMapper.findDetailById(articleId);
    }

    @Override
    public List<ArticleListDto> findListByCondition(ArticleSearch condition, int pageNum, int amount) {
        return articleMapper.findListByCondition(condition, pageNum, amount);
    }

    @Override
    public List<ArticleListDto> findListByMemberId(Long memberId, int pageNum, int amount) {
        return articleMapper.findListByMemberId(memberId, pageNum, amount);
    }

    @Override
    public int findTotalCount() {
        return articleMapper.findTotalCount();
    }
}
