package com.ssafy.goat.notion.service;

import com.ssafy.goat.notion.dto.NotionDto;

import java.util.List;

public interface NotionService {

    int addNotion(Long memberId, NotionDto notionDto);

    List<NotionDto> searchTopNotions();

    List<NotionDto> searchNotions(int pageNum, int amount);

    NotionDto searchNotion(Long notionId);

    int getTotalCount();

    int editNotion(Long notionId, Long memberId, NotionDto notionDto);

    int removeNotion(Long notionId, Long memberId);
}
