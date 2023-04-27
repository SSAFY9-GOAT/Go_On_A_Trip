package com.ssafy.goat.notion.repository;

import com.ssafy.goat.notion.Notion;

import java.util.List;
import java.util.Optional;

public interface NotionRepository {

    int save(Notion notion);

    Optional<Notion> findById(Long notionId);

    List<Notion> findAll();

    List<Notion> findTopAll();

    List<Notion> findByPaging(int pageNum, int amount);

    int getTotalCount();

    int update(Notion notion);

    int remove(Long notionId);

    void clear();
}
