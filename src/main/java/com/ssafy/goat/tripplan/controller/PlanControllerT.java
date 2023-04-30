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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/tripPlan")
public class PlanControllerT extends HttpServlet {

    private PlanService planService;
    private AttractionService attractionService;

//    @Override
//    public void init() throws ServletException {
//        planService = PlanServiceImpl.getPlanService();
//        attractionService = AttractionServiceImpl.getAttractionService();
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "mvcreate":
                doMvCreate(request, response);
                break;
            case "create":
                doCreate(request, response);
                break;
            case "list":
                doList(request, response);
                break;
            case "detail":
                doDetail(request, response);
                break;
            case "add":
                break;
            case "deletePlan":
                doRemovePlan(request, response);
                break;
            case "remove":
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    private void doMvCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        if (loginMember == null) {
            request.setAttribute("msg", "로그인 후 이용해주세요.");
            forward(request, response, "/account/login.jsp");
            return;
        }
        forward(request, response, "/tripplan/createPlan.jsp");
    }

    private void doCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        String title = request.getParameter("planTitle");
        String[] contentList = request.getParameter("contentList").split(",");
        List<Integer> contentIdList = new ArrayList<>();

        if(contentList.length<2){
            request.setAttribute("msg", "경로를 2개 이상 추가해주세요");
            forward(request, response, "/tripplan/createPlan.jsp");
            return;
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
        redirect(request, response, "/tripPlan?action=detail&tripPlanId=" + tripPlanId);
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        List<PlanListDto> plans = planService.searchPlans(planSearch, pageNum, amount);
        int totalCount = planService.getTotalCount();
        Page page = new Page(pageNum, amount, totalCount);

        request.setAttribute("page", page);
        request.setAttribute("plans", plans);
        forward(request, response, "/tripplan/tripList.jsp");
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long tripPlanId = Long.parseLong(request.getParameter("tripPlanId"));
        TripPlanDto tripPlan = planService.showPlan(tripPlanId);
        request.setAttribute("tripPlan", tripPlan);
        forward(request, response, "/tripplan/viewPlan.jsp");
    }

    private void doRemovePlan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        Long tripPlanId = Long.parseLong(request.getParameter("planId"));

        if (loginMember == null) {
            request.setAttribute("msg", "로그인 후 이용해주세요.");
            forward(request, response, "/account/login.jsp");
            return;
        }

        TripPlanDto tripPlan = planService.showPlan(tripPlanId);
        try {
            for (DetailPlanDto detailPlan : tripPlan.getDetailPlans()) {
                planService.removeDetailPlan(loginMember.getId(), detailPlan.getDetailPlanId());
            }
            planService.removeTripPlan(loginMember.getId(), tripPlanId);
        }catch (PlanException e){
            request.setAttribute("msg", "자신의 플랜만 삭제 가능합니다.");
            forward(request, response, "/tripplan/tripList.jsp");
            return;
        }
        redirect(request, response, "/tripPlan?action=list");
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + path);
    }
}
