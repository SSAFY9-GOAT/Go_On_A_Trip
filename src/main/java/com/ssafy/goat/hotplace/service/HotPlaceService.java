package com.ssafy.goat.hotplace.service;

import com.ssafy.goat.hotplace.dto.HotPlaceDetailDto;
import com.ssafy.goat.hotplace.dto.HotPlaceDto;
import com.ssafy.goat.hotplace.dto.HotPlaceListDto;
import com.ssafy.goat.hotplace.dto.HotPlaceSearch;

import java.util.List;

public interface HotPlaceService {

    int addHotPlace(Long memberId, int contentId, HotPlaceDto hotPlaceDto);

    HotPlaceDetailDto searchHotPlace(Long hotPlaceId);

    List<HotPlaceListDto> searchHotPlaces(HotPlaceSearch condition);

    List<HotPlaceListDto> searchHotPlaces(Long memberId);

    List<HotPlaceListDto> searchFavorites(Long memberId, int pageNum, int amount );

    int doFavorite(Long memberId, Long hotPlaceId);

    int editHotPlace(Long memberId, Long hotPlaceId, HotPlaceDto hotPlaceDto);

    int updateHit(Long hotPlaceId);

    int removeHotPlace(Long hotPlaceId, Long memberId);
}
