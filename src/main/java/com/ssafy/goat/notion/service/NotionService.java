package com.ssafy.goat.notion.service;

import com.ssafy.goat.notion.dto.NotionDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface NotionService {

    int addNotion(Long memberId, NotionDto notionDto);

    @Transactional(readOnly = true)
    List<NotionDto> searchTopNotions();

    @Transactional(readOnly = true)
    List<NotionDto> searchNotions(int pageNum, int amount);

    @Transactional(readOnly = true)
    NotionDto searchNotion(Long notionId);

    @Transactional(readOnly = true)
    int getTotalCount();

    int editNotion(Long notionId, Long memberId, NotionDto notionDto);

    int removeNotion(Long notionId, Long memberId);
}
