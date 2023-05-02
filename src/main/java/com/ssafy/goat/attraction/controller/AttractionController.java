package com.ssafy.goat.attraction.controller;

import com.ssafy.goat.attraction.dto.AttractionDto;
import com.ssafy.goat.attraction.dto.AttractionSearch;
import com.ssafy.goat.attraction.dto.GugunDto;
import com.ssafy.goat.attraction.dto.SidoDto;
import com.ssafy.goat.attraction.service.AttractionService;
import com.ssafy.goat.attraction.service.GugunService;
import com.ssafy.goat.attraction.service.SidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/attraction")
@RequiredArgsConstructor
@Slf4j
public class AttractionController {
    private final AttractionService attractionService;
    private final SidoService sidoService;
    private final GugunService gugunService;

    @GetMapping("/search")
    public String search(HttpServletRequest request, Model model){
        int sidoCode = getSidoCode(request);
        int gugunCode = getGugunCode(request);
        int contentTypeId = getContentTypeId(request);

        List<SidoDto> sidos = sidoService.findAll();
        model.addAttribute("sidos", sidos);

        List<GugunDto> guguns = gugunService.searchGuguns(gugunCode);
        model.addAttribute("guguns", guguns);

        AttractionSearch condition = AttractionSearch.builder()
                .sidoCode(sidoCode)
                .gugunCode(gugunCode)
                .contentTypeId(contentTypeId)
                .build();

        List<AttractionDto> attractions = attractionService.searchAttraction(condition);
        model.addAttribute("attractions", attractions);

        return "attraction/attractionList";
    }
    private int getSidoCode(HttpServletRequest request) {
        String sidoCode = request.getParameter("sidoCode");
        if (sidoCode == null) {
            return 1;
        }
        return Integer.parseInt(sidoCode);
    }

    private int getGugunCode(HttpServletRequest request) {
        String gugunCode = request.getParameter("gugunCode");
        if (gugunCode == null) {
            return 1;
        }
        return Integer.parseInt(gugunCode);
    }

    private int getContentTypeId(HttpServletRequest request) {
        String contentTypeId = request.getParameter("contentTypeId");
        if (contentTypeId == null) {
            return 12;
        }
        return Integer.parseInt(contentTypeId);
    }
}