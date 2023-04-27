package com.ssafy.goat.attraction.service;

import com.ssafy.goat.attraction.Sido;
import com.ssafy.goat.attraction.dto.SidoDto;
import com.ssafy.goat.attraction.repository.SidoJdbcRepository;
import com.ssafy.goat.attraction.repository.SidoRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SidoServiceImpl implements SidoService {

    private static final SidoService sidoService = new SidoServiceImpl();
    private final SidoRepository sidoRepository;

    private SidoServiceImpl() {
        sidoRepository = SidoJdbcRepository.getSidoRepository();
    }

    public static SidoService getSidoService() {
        return sidoService;
    }

    @Override
    public List<SidoDto> findAll() {
        List<Sido> sidos = sidoRepository.findAll();
        return sidos.stream()
                .map(sido -> new SidoDto(sido.getCode(), sido.getName()))
                .collect(Collectors.toList());
    }
}
