package com.ssafy.goat.hotplace.repository;

import com.ssafy.goat.hotplace.HotPlace;

import java.util.List;
import java.util.Optional;

public interface HotPlaceRepository {

    int save(HotPlace hotPlace);

    Optional<HotPlace> findById(Long hotPlaceId);

    int findContentIdById(Long hotPlaceId);

    List<HotPlace> findAll();

    int update(HotPlace hotPlace);

    int updateHit(HotPlace hotPlace);

    int remove(Long hotPlaceId);

    void clear();
}
