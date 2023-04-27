package com.ssafy.goat.attraction.service;

import attraction.Sido;
import attraction.dto.SidoDto;
import attraction.repository.SidoJdbcRepository;
import attraction.repository.SidoRepository;

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
