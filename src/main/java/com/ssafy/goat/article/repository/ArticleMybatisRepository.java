package com.ssafy.goat.article.repository;

import com.ssafy.goat.article.Article;
import com.ssafy.goat.article.mapper.ArticleMapper;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleMybatisRepository implements ArticleRepository {

    private final ArticleMapper articleMapper;
    private final MemberRepository memberRepository;

    @Override
    public int save(Article article) {
        return articleMapper.save(article);
    }

    @Override
    public Optional<Article> findById(Long articleId) {
//        Map<String, String> res = articleMapper.findById(articleId);
//        Article.builder()
//                .title(res.get("title"))
//                .member(new Member(res.get("member_id")))
        Optional<Article> findArticle = articleMapper.findById(articleId);
        if (findArticle.isPresent()) {
            Article article = findArticle.get();
            article.changeMember(article.getMemberId());
            return Optional.ofNullable(article);
        }
        return findArticle;
    }

    @Override
    public List<Article> findAll() {
        return articleMapper.findAll();
    }

    @Override
    public int update(Article article) {
        return articleMapper.update(article);
    }

    @Override
    public int updateHit(Article article) {
        return articleMapper.updateHit(article);
    }

    @Override
    public int remove(Long articleId) {
        return articleMapper.remove(articleId);
    }

    @Override
    public void clear() {

    }
}
