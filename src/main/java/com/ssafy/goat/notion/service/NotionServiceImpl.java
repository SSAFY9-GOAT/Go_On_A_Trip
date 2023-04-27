package com.ssafy.goat.notion.service;

import com.ssafy.goat.common.exception.NotionException;
import com.ssafy.goat.common.validation.NotionValidation;
import com.ssafy.goat.common.validation.dto.NotionRequest;
import com.ssafy.goat.member.Authority;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.repository.MemberJdbcRepository;
import com.ssafy.goat.member.repository.MemberRepository;
import com.ssafy.goat.notion.Notion;
import com.ssafy.goat.notion.dto.NotionDto;
import com.ssafy.goat.notion.repository.NotionJdbcRepository;
import com.ssafy.goat.notion.repository.NotionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NotionServiceImpl implements NotionService {

    private static final NotionService notionService = new NotionServiceImpl();
    private final NotionRepository notionRepository;
    private final MemberRepository memberRepository;

    private NotionServiceImpl() {
        notionRepository = NotionJdbcRepository.getNotionRepository();
        memberRepository = MemberJdbcRepository.getMemberRepository();
    }

    public static NotionService getNotionService() {
        return notionService;
    }


    @Override
    public int addNotion(Long memberId, NotionDto notionDto) {
        NotionValidation validation = new NotionValidation();

        NotionRequest request = NotionRequest.builder()
                .title(notionDto.getTitle())
                .content(notionDto.getContent())
                .build();
        validation.validate(request);

        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new NotionException();
        }

        Member member = findMember.get();
        if (member.getAuthority() != Authority.ADMIN) {
            throw new NotionException();
        }

        Notion notion = Notion.builder()
                .title(notionDto.getTitle())
                .content(notionDto.getContent())
                .top(notionDto.isTop())
                .createdBy(member)
                .lastModifiedBy(member)
                .build();

        return notionRepository.save(notion);
    }

    @Override
    public List<NotionDto> searchTopNotions() {
        List<Notion> topNotions = notionRepository.findTopAll();

        return topNotions.stream()
                .map(notion ->
                        NotionDto.builder()
                                .id(notion.getId())
                                .title(notion.getTitle())
                                .content(notion.getContent())
                                .createdDate(notion.getCreatedDate())
                                .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<NotionDto> searchNotions(int pageNum, int amount) {
        List<Notion> notions = notionRepository.findByPaging(pageNum, amount);

        return notions.stream()
                .map(notion -> NotionDto.builder()
                        .id(notion.getId())
                        .title(notion.getTitle())
                        .content(notion.getContent())
                        .createdDate(notion.getCreatedDate())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public NotionDto searchNotion(Long notionId) {
        Optional<Notion> findNotion = notionRepository.findById(notionId);
        if (!findNotion.isPresent()) {
            throw new NotionException();
        }
        Notion notion = findNotion.get();
        NotionDto notionDto = NotionDto.builder()
                .id(notion.getId())
                .title(notion.getTitle())
                .content(notion.getContent())
                .createdDate(notion.getCreatedDate())
                .build();
        return notionDto;
    }

    @Override
    public int getTotalCount() {
        return notionRepository.getTotalCount();
    }

    @Override
    public int editNotion(Long notionId, Long memberId, NotionDto notionDto) {
        NotionValidation validation = new NotionValidation();

        NotionRequest request = NotionRequest.builder()
                .title(notionDto.getTitle())
                .content(notionDto.getContent())
                .build();
        validation.validate(request);

        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new NotionException();
        }

        Member member = findMember.get();
        if (member.getAuthority() != Authority.ADMIN) {
            throw new NotionException();
        }

        Optional<Notion> findNotion = notionRepository.findById(notionId);
        if (!findNotion.isPresent()) {
            throw new NotionException();
        }

        Notion notion = findNotion.get();
        notion.edit(notionDto.getTitle(), notionDto.getContent(), member);

        return notionRepository.update(notion);
    }

    @Override
    public int removeNotion(Long notionId, Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new NotionException();
        }

        Member member = findMember.get();
        if (member.getAuthority() != Authority.ADMIN) {
            throw new NotionException();
        }

        Optional<Notion> findNotion = notionRepository.findById(notionId);
        if (!findNotion.isPresent()) {
            throw new NotionException();
        }

        return notionRepository.remove(notionId);
    }
}
