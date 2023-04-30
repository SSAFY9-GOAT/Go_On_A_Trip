package com.ssafy.goat.hotplace.service;

import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.attraction.repository.AttractionJdbcRepository;
import com.ssafy.goat.attraction.repository.AttractionRepository;
import com.ssafy.goat.common.exception.HotPlaceException;
import com.ssafy.goat.hotplace.HotPlace;
import com.ssafy.goat.hotplace.UploadFile;
import com.ssafy.goat.hotplace.dto.HotPlaceDetailDto;
import com.ssafy.goat.hotplace.dto.HotPlaceDto;
import com.ssafy.goat.hotplace.dto.HotPlaceListDto;
import com.ssafy.goat.hotplace.dto.HotPlaceSearch;
import com.ssafy.goat.hotplace.repository.HotPlaceJdbcRepository;
import com.ssafy.goat.hotplace.repository.HotPlaceQueryJdbcRepository;
import com.ssafy.goat.hotplace.repository.HotPlaceQueryRepository;
import com.ssafy.goat.hotplace.repository.HotPlaceRepository;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.repository.MemberJdbcRepository;
import com.ssafy.goat.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotPlaceServiceImpl implements HotPlaceService {
    private final HotPlaceRepository hotPlaceRepository;
    private final HotPlaceQueryRepository hotPlaceQueryRepository;
    private final MemberRepository memberRepository;
    private final AttractionRepository attractionRepository;

    @Override
    public int addHotPlace(Long memberId, int contentId, HotPlaceDto hotPlaceDto) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new HotPlaceException();
        }

        Optional<AttractionInfo> findAttractionInfo = attractionRepository.findById(contentId);
        if (!findAttractionInfo.isPresent()) {
            throw new HotPlaceException();
        }

        HotPlace hotPlace = HotPlace.builder()
                .contentTypeId(hotPlaceDto.getContentTypeId())
                .name(hotPlaceDto.getName())
                .desc(hotPlaceDto.getDesc())
                .visitedDate(hotPlaceDto.getVisitedDate())
                .uploadFile(
                        UploadFile.builder()
                                .uploadFileName(hotPlaceDto.getUploadFile().getUploadFileName())
                                .storeFileName(hotPlaceDto.getUploadFile().getStoreFileName())
                                .build()
                )
                .member(findMember.get())
                .attractionInfo(findAttractionInfo.get())
                .build();

        return hotPlaceRepository.save(hotPlace);
    }

    @Override
    public HotPlaceDetailDto searchHotPlace(Long hotPlaceId) {
        Optional<HotPlaceDetailDto> findHotPlace = hotPlaceQueryRepository.findDetailById(hotPlaceId);
        if (!findHotPlace.isPresent()) {
            throw new HotPlaceException();
        }
        return findHotPlace.get();
    }

    @Override
    public List<HotPlaceListDto> searchHotPlaces(HotPlaceSearch condition) {
        return hotPlaceQueryRepository.findByCondition(condition);
    }

    @Override
    public List<HotPlaceListDto> searchHotPlaces(Long memberId) {
        return hotPlaceQueryRepository.findByMemberId(memberId);
    }

    @Override
    public List<HotPlaceListDto> searchFavorites(Long memberId, int pageNum, int amount) {
        return hotPlaceQueryRepository.findFavoritesByMemberId(memberId, pageNum, amount);
    }

    @Override
    public int doFavorite(Long memberId, Long hotPlaceId) {
        return hotPlaceQueryRepository.doFavorite(memberId, hotPlaceId);
    }


    @Override
    public int editHotPlace(Long memberId, Long hotPlaceId, HotPlaceDto hotPlaceDto) {
        Optional<HotPlace> findHotPlace = hotPlaceRepository.findById(hotPlaceId);
        if (!findHotPlace.isPresent()) {
            throw new HotPlaceException();
        }

        HotPlace hotPlace = findHotPlace.get();
        if (!hotPlace.getMember().getId().equals(memberId)) {
            throw new HotPlaceException();
        }

        hotPlace.editContent(hotPlaceDto.getName(),
                hotPlaceDto.getDesc(),
                hotPlaceDto.getVisitedDate());

        return hotPlaceRepository.update(hotPlace);
    }

    @Override
    public int updateHit(Long hotPlaceId) {
        Optional<HotPlace> findHotPlace = hotPlaceRepository.findById(hotPlaceId);
        if (!findHotPlace.isPresent()) {
            throw new HotPlaceException();
        }

        HotPlace hotPlace = findHotPlace.get();
        hotPlace.increaseHit();

        return hotPlaceRepository.updateHit(hotPlace);
    }

    @Override
    public int removeHotPlace(Long hotPlaceId, Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new HotPlaceException();
        }

        Optional<HotPlace> findHotPlace = hotPlaceRepository.findById(hotPlaceId);
        if (!findHotPlace.isPresent()) {
            throw new HotPlaceException();
        }
        HotPlace hotPlace = findHotPlace.get();
        if (hotPlace.getMember().getId().equals(memberId)) {
            throw new HotPlaceException();
        }

        return hotPlaceRepository.remove(hotPlaceId);
    }
}
