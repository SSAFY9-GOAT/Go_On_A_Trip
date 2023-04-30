package com.ssafy.goat.hotplace.repository;

import com.ssafy.goat.hotplace.dto.HotPlaceDetailDto;
import com.ssafy.goat.hotplace.dto.HotPlaceListDto;
import com.ssafy.goat.hotplace.dto.HotPlaceSearch;
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
    private final HotPlaceQueryRepository hotPlaceQueryRepository;

    @Override
    public Optional<HotPlaceDetailDto> findDetailById(Long hotPlaceId) {
        return hotPlaceQueryRepository.findDetailById(hotPlaceId);
    }

    @Override
    public List<HotPlaceListDto> findByCondition(HotPlaceSearch condition) {
        return hotPlaceQueryRepository.findByCondition(condition);
    }

    @Override
    public List<HotPlaceListDto> findByMemberId(Long memberId) {
        return hotPlaceQueryRepository.findByMemberId(memberId);
    }

    @Override
    public List<HotPlaceListDto> findFavoritesByMemberId(Long memberId, int pageNum, int amount) {
        return hotPlaceQueryRepository.findFavoritesByMemberId(memberId, pageNum, amount);
    }

    @Override
    public int doFavorite(Long memberId, Long hotPlaceId) {
        return hotPlaceQueryRepository.doFavorite(memberId, hotPlaceId);

    }

    private String dateFormat(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
