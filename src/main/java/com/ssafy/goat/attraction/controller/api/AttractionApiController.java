package com.ssafy.goat.attraction.controller.api;

import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.attraction.dto.AttractionDto;
import com.ssafy.goat.attraction.dto.AttractionSearch;
import com.ssafy.goat.attraction.dto.GugunDto;
import com.ssafy.goat.attraction.service.AttractionService;
import com.ssafy.goat.attraction.service.GugunService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/attraction")
@RequiredArgsConstructor
@Slf4j
public class AttractionApiController {

    private final AttractionService attractionService;
    private final GugunService gugunService;

    @GetMapping(value = "/gugun/{sidoCode}")
    public ResponseEntity<?> doGugun(@PathVariable("sidoCode") int sidoCode, HttpServletRequest request) {
//        int sidoCode = Integer.parseInt(request.getParameter("sidoCode"));
        log.debug("시도 코드 : " + sidoCode);
        List<GugunDto> guguns = gugunService.searchGuguns(sidoCode);
        if (guguns != null && !guguns.isEmpty()) {
            log.debug("구군 size : " + guguns.size());
            Result<List<GugunDto>> result = new Result<>(guguns);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/search")
    public ResponseEntity<?> doSearch(
            @RequestParam int sidoCode,
            @RequestParam int gugunCode,
            @RequestParam int contentTypeId,
            HttpServletRequest request,
            Model model) {
//        int sidoCode = Integer.parseInt(request.getParameter("sidoCode"));
//        int gugunCode = Integer.parseInt(request.getParameter("gugunCode"));
//        int contentTypeId = Integer.parseInt(request.getParameter("contentTypeId"));

        AttractionSearch condition = AttractionSearch.builder().sidoCode(sidoCode).gugunCode(gugunCode).contentTypeId(contentTypeId).build();

        List<AttractionDto> attractions = attractionService.searchAttraction(condition);
        if (attractions != null && !attractions.isEmpty()) {
            return new ResponseEntity<>(attractions, HttpStatus.OK);
        } else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/hotplace")
    public ResponseEntity<?> doSearchHotPlace(@PathVariable(value = "title", required = false) String title) {
        List<AttractionDto> attractions = attractionService.searchAttraction(title);

        if (attractions != null && !attractions.isEmpty())
            return new ResponseEntity<List<AttractionDto>>(attractions, HttpStatus.OK);
        else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/tripPlan")
    public ResponseEntity<?> doSearchAttraction(@PathVariable("contentId") String contentId) {
        AttractionDto attraction = attractionService.searchAttraction(Integer.parseInt(contentId));

        if (attraction != null) return new ResponseEntity<AttractionDto>(attraction, HttpStatus.OK);
        else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/tripPlanList")
    public ResponseEntity<?> doTripPlanList(@PathVariable("title") String title) {
        List<AttractionDto> attractions = attractionService.searchAttraction(title);

        if (attractions != null && !attractions.isEmpty())
            return new ResponseEntity<List<AttractionDto>>(attractions, HttpStatus.OK);
        else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
