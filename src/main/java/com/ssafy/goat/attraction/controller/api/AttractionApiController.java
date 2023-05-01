package com.ssafy.goat.attraction.controller.api;

import com.ssafy.goat.attraction.dto.AttractionDto;
import com.ssafy.goat.attraction.dto.AttractionSearch;
import com.ssafy.goat.attraction.dto.GugunDto;
import com.ssafy.goat.attraction.service.AttractionService;
import com.ssafy.goat.attraction.service.GugunService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/attraction")
@RequiredArgsConstructor
public class AttractionApiController{

    private final AttractionService attractionService;
    private final GugunService gugunService;

    @GetMapping(value = "/gugun")
    public ResponseEntity<?> doGugun(@PathVariable("sidoCode") String sidoCodeS){
        int sidoCode = Integer.parseInt(sidoCodeS);
        List<GugunDto> guguns = gugunService.searchGuguns(sidoCode);
        if(guguns!=null && !guguns.isEmpty())
            return new ResponseEntity<List<GugunDto>>(guguns, HttpStatus.OK);
        else
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<?> doSearch(Model model){
        int sidoCode = Integer.parseInt((String) model.getAttribute("sidoCode"));
        int gugunCode = Integer.parseInt((String) model.getAttribute("gugunCode"));
        int contentTypeId = Integer.parseInt((String) model.getAttribute("contentTypeId"));

        AttractionSearch condition = AttractionSearch.builder()
                .sidoCode(sidoCode)
                .gugunCode(gugunCode)
                .contentTypeId(contentTypeId)
                .build();

        List<AttractionDto> attractions = attractionService.searchAttraction(condition);
        if(attractions != null && !attractions.isEmpty())
            return new ResponseEntity<List<AttractionDto>>(attractions, HttpStatus.OK);
        else
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/hotplace")
    public ResponseEntity<?> doSearchHotPlace(@PathVariable("title") String title){
        List<AttractionDto> attractions = attractionService.searchAttraction(title);

        if(attractions != null && !attractions.isEmpty())
            return new ResponseEntity<List<AttractionDto>>(attractions, HttpStatus.OK);
        else
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(value = "/tripPlan")
    public ResponseEntity<?> doSearchAttraction(@PathVariable("contentId") String contentId){
        AttractionDto attraction = attractionService.searchAttraction(Integer.parseInt(contentId));

        if(attraction != null)
            return new ResponseEntity<AttractionDto>(attraction, HttpStatus.OK);
        else
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(value = "/tripPlanList")
    public ResponseEntity<?> doTripPlanList(@PathVariable("title") String title){
        List<AttractionDto> attractions = attractionService.searchAttraction(title);

        if(attractions != null && !attractions.isEmpty())
            return new ResponseEntity<List<AttractionDto>>(attractions, HttpStatus.OK);
        else
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
