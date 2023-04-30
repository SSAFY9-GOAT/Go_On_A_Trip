package com.ssafy.goat.attraction.service;

import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.attraction.dto.AttractionDto;
import com.ssafy.goat.attraction.dto.AttractionSearch;
import com.ssafy.goat.attraction.repository.AttractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {
    private final AttractionRepository attractionRepository;

    @Override
    public AttractionDto searchAttraction(int contentId) {
        Optional<AttractionInfo> findAttractionInfo = attractionRepository.findById(contentId);
        if (!findAttractionInfo.isPresent()) {
            throw new RuntimeException();
        }
        AttractionInfo attractionInfo = findAttractionInfo.get();

        return AttractionDto.builder()
                .id(attractionInfo.getId())
                .title(attractionInfo.getTitle())
                .latitude(attractionInfo.getLatitude())
                .longitude(attractionInfo.getLongitude())
                .build();
    }

    @Override
    public List<AttractionDto> searchAttraction(AttractionSearch condition) {
        List<AttractionInfo> findAttractionInfos = attractionRepository.findByConditions(condition);
        return findAttractionInfos.stream()
                .map(attractionInfo ->
                        AttractionDto.builder()
                                .title(attractionInfo.getTitle())
                                .addr1(attractionInfo.getAddr1())
                                .zipcode(attractionInfo.getZipcode())
                                .firstImage(attractionInfo.getFirstImage())
                                .latitude(attractionInfo.getLatitude())
                                .longitude(attractionInfo.getLongitude())
                                .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<AttractionDto> searchAttraction(String title) {
        List<AttractionInfo> findAttractionInfos = attractionRepository.findByTitle(title);
        return findAttractionInfos.stream()
                .map(attractionInfo ->
                        AttractionDto.builder()
                                .id(attractionInfo.getId())
                                .contentTypeId(attractionInfo.getContentTypeId())
                                .title(attractionInfo.getTitle())
                                .addr1(attractionInfo.getAddr1())
                                .zipcode(attractionInfo.getZipcode())
                                .firstImage(attractionInfo.getFirstImage())
                                .latitude(attractionInfo.getLatitude())
                                .longitude(attractionInfo.getLongitude())
                                .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<AttractionInfo> searchAttraction(List<Integer> contentIds) {
        return attractionRepository.findByContentIds(contentIds);
    }
}
