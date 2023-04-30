package com.ssafy.goat.hotplace.mapper;

import com.ssafy.goat.hotplace.HotPlace;
import com.ssafy.goat.hotplace.dto.HotPlaceDetailDto;
import com.ssafy.goat.hotplace.dto.HotPlaceListDto;
import com.ssafy.goat.hotplace.dto.HotPlaceSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface HotplaceMapper {

    int save(HotPlace hotPlace);

    Optional<HotPlace> findById(Long hotPlaceId);

    List<HotPlace> findAll();

    int update(HotPlace hotPlace);

    int updateHit(HotPlace hotPlace);

    int remove(Long hotPlaceId);

    void clear();

    Optional<HotPlaceDetailDto> findDetailById(Long hotPlaceId);

    List<HotPlaceListDto> findByCondition(HotPlaceSearch condition);

    List<HotPlaceListDto> findByMemberId(Long memberId);

    List<HotPlaceListDto> findFavoritesByMemberId(Long memberId, int pageNum, int amount);

    int doFavorite(Long memberId, Long hotPlaceId);
}
