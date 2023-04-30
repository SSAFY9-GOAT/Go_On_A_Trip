package com.ssafy.goat.attraction.service;

import com.ssafy.goat.attraction.Gugun;
import com.ssafy.goat.attraction.dto.GugunDto;
import com.ssafy.goat.attraction.repository.GugunJdbcRepository;
import com.ssafy.goat.attraction.repository.GugunRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GugunServiceImplT implements GugunService {

    private static final GugunService gugunService = new GugunServiceImplT();
    private final GugunRepository gugunRepository;

    private GugunServiceImplT() {
        gugunRepository = GugunJdbcRepository.getGugunRepository();
    }

    public static GugunService getGugunService() {
        return gugunService;
    }

    @Override
    public List<GugunDto> searchGuguns(int sidoCode) {
        List<Gugun> findGuguns = gugunRepository.findBySidoCode(sidoCode);
        return findGuguns.stream()
                .map(gugun -> new GugunDto(gugun.getCode(), gugun.getName()))
                .collect(Collectors.toList());
    }
}
