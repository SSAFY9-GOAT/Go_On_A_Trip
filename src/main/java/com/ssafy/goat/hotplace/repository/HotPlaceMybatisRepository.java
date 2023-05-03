package com.ssafy.goat.hotplace.repository;

import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.hotplace.HotPlace;
import com.ssafy.goat.hotplace.UploadFile;
import com.ssafy.goat.hotplace.mapper.HotplaceMapper;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.util.DBConnectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ssafy.goat.member.Authority.ADMIN;
import static com.ssafy.goat.member.Authority.CLIENT;

@Repository
@RequiredArgsConstructor
public class HotPlaceMybatisRepository implements HotPlaceRepository {
    private final HotplaceMapper hotplaceMapper;

    @Override
    public int save(HotPlace hotPlace) {
        return hotplaceMapper.save(hotPlace);
    }

    @Override
    public Optional<HotPlace> findById(Long hotPlaceId) {
        return hotplaceMapper.findById(hotPlaceId);
    }

    @Override
    public int findContentIdById(Long hotPlaceId) {
        return hotplaceMapper.findContentIdById(hotPlaceId);
    }

    @Override
    public List<HotPlace> findAll() {
        return hotplaceMapper.findAll();
    }

    @Override
    public int update(HotPlace hotPlace) {
        return hotplaceMapper.update(hotPlace);
    }

    @Override
    public int updateHit(HotPlace hotPlace) {
        return hotplaceMapper.updateHit(hotPlace);
    }

    @Override
    public int remove(Long hotPlaceId) {
        return hotplaceMapper.remove(hotPlaceId);
    }

    @Override
    public void clear() {
        hotplaceMapper.clear();
    }
}
