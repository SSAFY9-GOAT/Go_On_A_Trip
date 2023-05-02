package com.ssafy.goat.tripplan.controller;

import com.ssafy.goat.algorithm.ShortestPath;
import com.ssafy.goat.attraction.AttractionInfo;
import com.ssafy.goat.attraction.service.AttractionService;
import com.ssafy.goat.common.Page;
import com.ssafy.goat.common.exception.PlanException;
import com.ssafy.goat.member.dto.LoginMember;
import com.ssafy.goat.tripplan.dto.DetailPlanDto;
import com.ssafy.goat.tripplan.dto.PlanListDto;
import com.ssafy.goat.tripplan.dto.PlanSearch;
import com.ssafy.goat.tripplan.dto.TripPlanDto;
import com.ssafy.goat.tripplan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/tripPlan")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;
    private final AttractionService attractionService;

    @GetMapping("/create")
    public String doMvCreate(HttpServletRequest request, @SessionAttribute(name = "userinfo") LoginMember loginMember, Model model) {
        if (loginMember == null) {
            request.setAttribute("msg", "로그인 후 이용해주세요.");
            return "account/login";
        }
        return "tripplan/createPlan";
    }

    @PostMapping("/create")
    public String doCreate(HttpServletRequest request, @SessionAttribute(name = "userinfo") LoginMember loginMember, Model model) {
        String title = request.getParameter("planTitle");
        String[] contentList = request.getParameter("contentList").split(",");
        List<Integer> contentIdList = new ArrayList<>();

        if (contentList.length < 2) {
            request.setAttribute("msg", "경로를 2개 이상 추가해주세요");
            return "tripplan/createPlan";
        }
        for (String contentId : contentList) {
            contentIdList.add(Integer.parseInt(contentId));
        }

        planService.addTripPlan(loginMember.getId(), title);
        Long tripPlanId = planService.getTripPlanId(loginMember.getId());

        List<AttractionInfo> attractionInfos = attractionService.searchAttraction(contentIdList);
        ShortestPath shortestPath = new ShortestPath();
        List<AttractionInfo> shortestPaths = shortestPath.getShortestPath(attractionInfos);
        for (AttractionInfo path : shortestPaths) {
            planService.addDetailPlan(loginMember.getId(), tripPlanId, path.getId());
        }
//        model.addAttribute("tripPlanId", tripPlanId);
        return "redirect:/tripPlan/detail/" + tripPlanId;
    }

    @GetMapping("/list")
    public String doList(HttpServletRequest request, Model model) {
        String condition = request.getParameter("condition") == null ? "" : request.getParameter("condition");

        int pageNum = 1;
        int amount = 10;

        if (request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
            amount = Integer.parseInt(request.getParameter("amount"));
        }

        PlanSearch planSearch = PlanSearch.builder()
                .condition(condition)
                .build();

        List<PlanListDto> plans = planService.searchPlans(planSearch, (pageNum - 1)*amount, amount);
        int totalCount = planService.getTotalCount();
        Page page = new Page(pageNum, amount, totalCount);

        model.addAttribute("page", page);
        model.addAttribute("plans", plans);
        return "tripplan/tripList";
    }

    @GetMapping("/detail/{tripPlanId}")
    public String doDetail(HttpServletRequest request, @PathVariable long tripPlanId, @SessionAttribute(name = "userinfo") LoginMember loginMember, Model model) {
        TripPlanDto tripPlan = planService.showPlan(tripPlanId);
        model.addAttribute("tripPlan", tripPlan);
        return "tripplan/viewPlan";
    }

    @GetMapping("/deletePlan/tripPlanId")
    public String doRemovePlan(HttpServletRequest request, @SessionAttribute(name = "userinfo") LoginMember loginMember, Model model) {
        Long tripPlanId = Long.parseLong(request.getParameter("planId"));
        if (loginMember == null) {
            model.addAttribute("msg", "로그인 후 이용해주세요.");
            return "account/login";
        }

        TripPlanDto tripPlan = planService.showPlan(tripPlanId);
        try {
            for (DetailPlanDto detailPlan : tripPlan.getDetailPlans()) {
                planService.removeDetailPlan(loginMember.getId(), detailPlan.getDetailPlanId());
            }
            planService.removeTripPlan(loginMember.getId(), tripPlanId);
        } catch (PlanException e) {
            request.setAttribute("msg", "자신의 플랜만 삭제 가능합니다.");
            return "tripPlan/tripList";
        }
        return "redirect:/tripPlan/list";
    }
}
