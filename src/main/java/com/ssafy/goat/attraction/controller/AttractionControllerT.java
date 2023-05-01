//package com.ssafy.goat.attraction.controller;
//
//import com.ssafy.goat.attraction.dto.AttractionDto;
//import com.ssafy.goat.attraction.dto.AttractionSearch;
//import com.ssafy.goat.attraction.dto.GugunDto;
//import com.ssafy.goat.attraction.dto.SidoDto;
//import com.ssafy.goat.attraction.service.*;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/attraction")
//public class AttractionControllerT extends HttpServlet {
//
//    private AttractionService attractionService;
//    private SidoService sidoService;
//    private GugunService gugunService;
//
//    @Override
//    public void init() {
////        attractionService = AttractionServiceImpl.getAttractionService();
////        sidoService = SidoServiceImpl.getSidoService();
////        gugunService = GugunServiceImpl.getGugunService();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        switch (action) {
//            case "search":
//                search(request, response);
//                break;
//        }
//    }
//
//    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int sidoCode = getSidoCode(request);
//        int gugunCode = getGugunCode(request);
//        int contentTypeId = getContentTypeId(request);
//
//        List<SidoDto> sidos = sidoService.findAll();
//        request.setAttribute("sidos", sidos);
//
//        List<GugunDto> guguns = gugunService.searchGuguns(gugunCode);
//        request.setAttribute("guguns", guguns);
//
//        AttractionSearch condition = AttractionSearch.builder()
//                .sidoCode(sidoCode)
//                .gugunCode(gugunCode)
//                .contentTypeId(contentTypeId)
//                .build();
//
//        List<AttractionDto> attractions = attractionService.searchAttraction(condition);
//        request.setAttribute("attractions", attractions);
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/attraction/attractionList.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    private int getSidoCode(HttpServletRequest request) {
//        String sidoCode = request.getParameter("sidoCode");
//        if (sidoCode == null) {
//            return 1;
//        }
//        return Integer.parseInt(sidoCode);
//    }
//
//    private int getGugunCode(HttpServletRequest request) {
//        String gugunCode = request.getParameter("gugunCode");
//        if (gugunCode == null) {
//            return 1;
//        }
//        return Integer.parseInt(gugunCode);
//    }
//
//    private int getContentTypeId(HttpServletRequest request) {
//        String contentTypeId = request.getParameter("contentTypeId");
//        if (contentTypeId == null) {
//            return 12;
//        }
//        return Integer.parseInt(contentTypeId);
//    }
//}
