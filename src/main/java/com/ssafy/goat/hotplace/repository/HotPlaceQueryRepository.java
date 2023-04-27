package com.ssafy.goat.hotplace.repository;

import hotplace.dto.HotPlaceDetailDto;
import hotplace.dto.HotPlaceListDto;
import hotplace.dto.HotPlaceSearch;

import java.util.List;
import java.util.Optional;

public interface HotPlaceQueryRepository {

    Optional<HotPlaceDetailDto> findDetailById(Long hotPlaceId);

    List<HotPlaceListDto> findByCondition(HotPlaceSearch condition);

    List<HotPlaceListDto> findByMemberId(Long memberId);

    List<HotPlaceListDto> findFavoritesByMemberId(Long memberId, int pageNum, int amount);

    int doFavorite(Long memberId, Long hotPlaceId);

}
