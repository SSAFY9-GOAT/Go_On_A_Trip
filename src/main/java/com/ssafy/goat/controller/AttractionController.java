package com.ssafy.goat.controller;

import com.ssafy.goat.attraction.dto.AttractionDto;
import com.ssafy.goat.attraction.dto.AttractionSearch;
import com.ssafy.goat.attraction.dto.GugunDto;
import com.ssafy.goat.attraction.dto.SidoDto;
import com.ssafy.goat.attraction.service.AttractionService;
import com.ssafy.goat.attraction.service.GugunService;
import com.ssafy.goat.attraction.service.SidoService;
import com.ssafy.goat.common.Page;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/attraction")
@RequiredArgsConstructor
@Slf4j
public class AttractionController {
    private final AttractionService attractionService;
    private final SidoService sidoService;
    private final GugunService gugunService;

    @GetMapping("/search")
    public ResultAttraction search(
            @RequestParam("sidoCode") String getSidoCode,
            @RequestParam("gugunCode") String getGugunCode,
            @RequestParam("contentTypeId") String getContentTypeId
            ){
        int sidoCode = getSidoCode==null?1: Integer.parseInt(getSidoCode);
        int gugunCode = getGugunCode==null?1: Integer.parseInt(getGugunCode);
        int contentTypeId = getContentTypeId==null?12: Integer.parseInt(getContentTypeId);

        List<SidoDto> sidos =sidoService.findAll();
        List<GugunDto> guguns = gugunService.searchGuguns(gugunCode);

        AttractionSearch condition = AttractionSearch.builder()
                .sidoCode(sidoCode)
                .gugunCode(gugunCode)
                .contentTypeId(contentTypeId)
                .build();

        List<AttractionDto> attractionDtos = attractionService.searchAttraction(condition);

        return new ResultAttraction(sidos, guguns,attractionDtos);
    }

    @Data
    @AllArgsConstructor
    static class ResultAttraction {
        List<SidoDto> sidos;
        List<GugunDto> guguns;
        List<AttractionDto> attractionDtos;
    }
}
