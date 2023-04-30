package com.ssafy.goat.article.repository;

import com.ssafy.goat.article.dto.ArticleDetailDto;
import com.ssafy.goat.article.dto.ArticleListDto;
import com.ssafy.goat.article.dto.ArticleSearch;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository

public class AticleQueryRepositoryImpl implements ArticleQueryRepository{


    @Override
    public Optional<ArticleDetailDto> findDetailById(Long articleId) {
        return Optional.empty();
    }

    @Override
    public List<ArticleListDto> findListByCondition(ArticleSearch condition, int pageNum, int amount) {
        return null;
    }

    @Override
    public List<ArticleListDto> findListByMemberId(Long memberId, int pageNum, int amount) {
        return null;
    }

    @Override
    public int findTotalCount() {
        return 0;
    }
}
