package com.ssafy.goat.attraction.service;

import attraction.Gugun;
import attraction.dto.GugunDto;
import attraction.repository.GugunJdbcRepository;
import attraction.repository.GugunRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GugunServiceImpl implements GugunService {

    private static final GugunService gugunService = new GugunServiceImpl();
    private final GugunRepository gugunRepository;

    private GugunServiceImpl() {
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
