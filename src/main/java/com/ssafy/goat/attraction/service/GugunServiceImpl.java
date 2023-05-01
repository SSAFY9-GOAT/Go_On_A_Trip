package com.ssafy.goat.attraction.service;

import com.ssafy.goat.attraction.Gugun;
import com.ssafy.goat.attraction.dto.GugunDto;
import com.ssafy.goat.attraction.repository.GugunJdbcRepository;
import com.ssafy.goat.attraction.repository.GugunRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GugunServiceImpl implements GugunService {

    private final GugunRepository gugunRepository;
    @Override
    public List<GugunDto> searchGuguns(int sidoCode) {
        List<Gugun> findGuguns = gugunRepository.findBySidoCode(sidoCode);

        return findGuguns.stream()
                .map(gugun -> new GugunDto(gugun.getCode(), gugun.getName()))
                .collect(Collectors.toList());
    }
}