package com.ssafy.goat.article.repository;

import com.ssafy.goat.article.Article;
import com.ssafy.goat.article.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleMybatisRepository implements ArticleRepository {

    private final ArticleMapper articleMapper;

    @Override
    public int save(Article article) {
        return articleMapper.save(article);
    }

    @Override
    public Optional<Article> findById(Long articleId) {
        return Optional.empty();
    }

    @Override
    public List<Article> findAll() {
        return null;
    }

    @Override
    public int update(Article article) {
        return 0;
    }

    @Override
    public int updateHit(Article article) {
        return 0;
    }

    @Override
    public int remove(Long articleId) {
        return 0;
    }

    @Override
    public void clear() {

    }
}
