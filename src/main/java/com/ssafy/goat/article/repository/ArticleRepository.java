package com.ssafy.goat.article.repository;



import com.ssafy.goat.article.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    int save(Article article);

    Optional<Article> findById(Long articleId);

    List<Article> findAll();

    int update(Article article);

    int updateHit(Article article);

    int remove(Long articleId);

    void clear();
}
