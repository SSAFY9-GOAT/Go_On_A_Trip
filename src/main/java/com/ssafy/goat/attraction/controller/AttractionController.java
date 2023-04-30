package com.ssafy.goat.attraction.controller;

import com.ssafy.goat.attraction.dto.AttractionDto;
import com.ssafy.goat.attraction.dto.AttractionSearch;
import com.ssafy.goat.attraction.dto.GugunDto;
import com.ssafy.goat.attraction.dto.SidoDto;
import com.ssafy.goat.attraction.service.AttractionService;
import com.ssafy.goat.attraction.service.GugunService;
import com.ssafy.goat.attraction.service.SidoService;
import lombok.RequiredArgsConstructor;
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
public class AttractionController {
    private final AttractionService attractionService;
    private final SidoService sidoService;
    private final GugunService gugunService;

    @GetMapping("/search")
    public String search(@RequestParam("sidoCode") String sidoCodeS, @RequestParam("gugunCode") String gugunCodeS, @RequestParam("contentTypeId") String contentTypeIdS, Model model){
        int sidoCode = getSidoCode(sidoCodeS);
        int gugunCode = getGugunCode(gugunCodeS);
        int contentTypeId = getContentTypeId(contentTypeIdS);

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

        return "attractionList";
    }
    private int getSidoCode(String sidoCodeS) {
        if (sidoCodeS == null) {
            return 1;
        }
        return Integer.parseInt(sidoCodeS);
    }

    private int getGugunCode(String gugunCodeS) {
        if (gugunCodeS == null) {
            return 1;
        }
        return Integer.parseInt(gugunCodeS);
    }

    private int getContentTypeId(String contentTypeIdS) {
        if (contentTypeIdS == null) {
            return 12;
        }
        return Integer.parseInt(contentTypeIdS);
    }
}