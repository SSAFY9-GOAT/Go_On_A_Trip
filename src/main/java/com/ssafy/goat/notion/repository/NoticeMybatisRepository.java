package com.ssafy.goat.notion.repository;

import com.ssafy.goat.notion.Notion;
import com.ssafy.goat.notion.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NoticeMybatisRepository implements NotionRepository{

    private final NoticeMapper noticeMapper;
    @Override
    public int save(Notion notion) {
        return noticeMapper.save(notion);
    }

    @Override
    public Optional<Notion> findById(Long notionId) {
        return noticeMapper.findById(notionId);
    }

    @Override
    public List<Notion> findAll() {
        return noticeMapper.findAll();
    }

    @Override
    public List<Notion> findTopAll() {
        return noticeMapper.findTopAll();
    }

    @Override
    public List<Notion> findByPaging(int pageNum, int amount) {
        return noticeMapper.findByPaging(pageNum, amount);
    }

    @Override
    public int getTotalCount() {
        return noticeMapper.getTotalCount();
    }

    @Override
    public int update(Notion notion) {
        return noticeMapper.update(notion);
    }

    @Override
    public int remove(Long notionId) {
        return noticeMapper.remove(notionId);
    }

    @Override
    public void clear() {

    }
}
