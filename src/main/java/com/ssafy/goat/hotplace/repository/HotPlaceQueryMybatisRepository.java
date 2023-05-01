package com.ssafy.goat.hotplace.repository;

import com.ssafy.goat.hotplace.dto.HotPlaceDetailDto;
import com.ssafy.goat.hotplace.dto.HotPlaceListDto;
import com.ssafy.goat.hotplace.dto.HotPlaceSearch;
import com.ssafy.goat.hotplace.mapper.HotplaceMapper;
import com.ssafy.goat.util.DBConnectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HotPlaceQueryMybatisRepository implements HotPlaceQueryRepository {

    private final HotplaceMapper mapper;

//    private final HotPlaceQueryRepository hotPlaceQueryRepository;

    @Override
    public Optional<HotPlaceDetailDto> findDetailById(Long hotPlaceId) {
        return mapper.findDetailById(hotPlaceId);
    }

    @Override
    public List<HotPlaceListDto> findByCondition(HotPlaceSearch condition) {
        return mapper.findByCondition(condition);
    }

    @Override
    public List<HotPlaceListDto> findByMemberId(Long memberId) {
        return mapper.findByMemberId(memberId);
    }

    @Override
    public List<HotPlaceListDto> findFavoritesByMemberId(Long memberId, int pageNum, int amount) {
        return mapper.findFavoritesByMemberId(memberId, pageNum, amount);
    }

    @Override
    public int doFavorite(Long memberId, Long hotPlaceId) {
        return mapper.doFavorite(memberId, hotPlaceId);

    }

    private String dateFormat(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
