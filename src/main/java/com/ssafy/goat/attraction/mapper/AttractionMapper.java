package com.ssafy.goat.attraction.mapper;

import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.attraction.Gugun;
import com.ssafy.goat.attraction.Sido;
import com.ssafy.goat.attraction.dto.AttractionSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AttractionMapper {

    Optional<AttractionInfo> findById(int contentId);

    List<AttractionInfo> findByConditions(AttractionSearch condition);

    List<AttractionInfo> findByTitle(String title);

    List<AttractionInfo> findByContentIds(List<Integer> contentIds);
    List<Gugun> findBySidoCode(int sidoCode);
    List<Sido> findAll();
}
