package com.ssafy.goat.notion.mapper;

import com.ssafy.goat.notion.Notion;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface NoticeMapper {
    int save(Notion notion);

    Optional<Notion> findById(Long notionId);

    List<Notion> findAll();

    List<Notion> findTopAll();

    List<Notion> findByPaging(@Param("pageNum") int pageNum, @Param("amount") int amount);

    int getTotalCount();

    int update(Notion notion);

    int remove(Long notionId);

    void clear();
}
