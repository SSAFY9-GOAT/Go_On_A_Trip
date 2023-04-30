package com.ssafy.goat.notion.repository;

import com.ssafy.goat.member.Member;
import com.ssafy.goat.notion.Notion;
import com.ssafy.goat.notion.mapper.NoticeMapper;
//import com.ssafy.goat.notion.mapper.dto.NotionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
//        Optional<NotionDto> dto = noticeMapper.findById(notionId);
//                .get();

        return noticeMapper.findById(notionId);
    }

    @Override
    public List<Notion> findAll() {
//        List<NotionDto> notions = noticeMapper.findAll();
//        return getNotionList(notions);
        return noticeMapper.findAll();
    }

//    private static List<Notion> getNotionList(List<NotionDto> notions) {
//        List<Notion> list = new ArrayList<>();
//        for (NotionDto notionDto : notions) {
//            Notion notion = Notion.builder()
//                    .id(notionDto.getId())
//                    .title(notionDto.getTitle())
//                    .content(notionDto.getContent())
//                    .hit(notionDto.getHit())
//                    .top(notionDto.isTop())
//                    .createdBy(new Member(notionDto.getCreatedBy()))
//                    .lastModifiedBy(new Member(notionDto.getLastModifiedBy()))
//                    .createdDate(notionDto.getCreatedDate())
//                    .lastModifiedDate(notionDto.getLastModifiedDate())
//                    .build();
//            list.add(notion);
//        }
//        return list;
//    }

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
