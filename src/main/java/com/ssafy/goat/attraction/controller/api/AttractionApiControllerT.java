package com.ssafy.goat.attraction.controller.api;//package com.ssafy.goat.attraction.controller.api;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/api/attraction")
//public class AttractionApiController extends HttpServlet {
//
//    private AttractionService attractionService;
//    private GugunService gugunService;
//
//    @Override
//    public void init() {
//        attractionService = AttractionServiceImpl.getAttractionService();
//        gugunService = GugunServiceImpl.getGugunService();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        String action = request.getParameter("action");
//        System.out.println("action = " + action);
//        switch (action) {
//            case "gugun":
//                doGugun(request, response);
//                break;
//            case "search":
//                doSearch(request, response);
//                break;
//            case "hotplace":
//                doSearchHotPlace(request, response);
//                break;
//            case "tripPlan":
//                doSearchAttraction(request, response);
//                break;
//            case "tripPlanList":
//                doTripPlanList(request, response);
//                break;
//        }
//    }
//
//    private void doSearchAttraction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int contentId = Integer.parseInt(request.getParameter("contentId"));
//        AttractionDto attraction = attractionService.searchAttraction(contentId);
//
//        JSONObject json = new JSONObject();
//        JSONArray array = new JSONArray();
//        JSONObject temp = new JSONObject();
//        temp.put("contentId", attraction.getId());
//        temp.put("title", attraction.getTitle());
//        temp.put("latitude", attraction.getLatitude());
//        temp.put("longitude", attraction.getLongitude());
//        array.add(temp);
//        json.put("data", array);
//
//        response.setContentType("application/json; charset=UTF-8");
//        response.getWriter().println(json);
//    }
//
//    private void doGugun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int sidoCode = Integer.parseInt(request.getParameter("sidoCode"));
//
//        List<GugunDto> guguns = gugunService.searchGuguns(sidoCode);
//
//        JSONObject json = new JSONObject();
//        JSONArray array = new JSONArray();
//        for (GugunDto gugun : guguns) {
//            JSONObject temp = new JSONObject();
//            temp.put("code", gugun.getCode());
//            temp.put("name", gugun.getName());
//            array.add(temp);
//        }
//        json.put("data", array);
//
//        response.setContentType("application/json; charset=UTF-8");
//        response.getWriter().println(json);
//    }
//
//    private void doSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int sidoCode = Integer.parseInt(request.getParameter("sidoCode"));
//        int gugunCode = Integer.parseInt(request.getParameter("gugunCode"));
//        int contentTypeId = Integer.parseInt(request.getParameter("contentTypeId"));
//
//        AttractionSearch condition = AttractionSearch.builder()
//                .sidoCode(sidoCode)
//                .gugunCode(gugunCode)
//                .contentTypeId(contentTypeId)
//                .build();
//
//        List<AttractionDto> attractions = attractionService.searchAttraction(condition);
//
//        JSONObject json = new JSONObject();
//        JSONArray array = new JSONArray();
//        for (AttractionDto attraction : attractions) {
//            JSONObject temp = new JSONObject();
//            temp.put("title",attraction.getTitle());
//            temp.put("addr1",attraction.getAddr1());
//            temp.put("zipcode",attraction.getZipcode());
//            temp.put("firstImage",attraction.getFirstImage());
//            temp.put("latitude",attraction.getLatitude());
//            temp.put("longitude",attraction.getLongitude());
//            array.add(temp);
//        }
//        json.put("data", array);
//
//        response.setContentType("application/json; charset=UTF-8");
//        response.getWriter().println(json);
//    }
//
//    private void doSearchHotPlace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String title = request.getParameter("title");
//
//        List<AttractionDto> attractions = attractionService.searchAttraction(title);
//
//        JSONObject json = new JSONObject();
//        JSONArray array = new JSONArray();
//        for (AttractionDto attraction : attractions) {
//            JSONObject temp = new JSONObject();
//            temp.put("id", attraction.getId());
//            temp.put("contentTypeId", attraction.getContentTypeId());
//            temp.put("title",attraction.getTitle());
//            temp.put("addr1",attraction.getAddr1());
//            temp.put("zipcode",attraction.getZipcode());
//            temp.put("firstImage",attraction.getFirstImage());
//            temp.put("latitude",attraction.getLatitude());
//            temp.put("longitude",attraction.getLongitude());
//            array.add(temp);
//        }
//        json.put("data", array);
//
//        response.setContentType("application/json; charset=UTF-8");
//        response.getWriter().println(json);
//    }
//
//    private void doTripPlanList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String title = request.getParameter("title");
//        System.out.println("AttractionApiController.doTripPlanList");
//        List<AttractionDto> attractions = attractionService.searchAttraction(title);
//
//        JSONObject json = new JSONObject();
//        JSONArray array = new JSONArray();
//        for (AttractionDto attraction : attractions) {
//            JSONObject temp = new JSONObject();
//            temp.put("id", attraction.getId());
//            temp.put("contentTypeId", attraction.getContentTypeId());
//            temp.put("title",attraction.getTitle());
//            temp.put("addr1",attraction.getAddr1());
//            temp.put("zipcode",attraction.getZipcode());
//            temp.put("firstImage",attraction.getFirstImage());
//            temp.put("latitude",attraction.getLatitude());
//            temp.put("longitude",attraction.getLongitude());
//            array.add(temp);
//        }
//        json.put("data", array);
//
//        response.setContentType("application/json; charset=UTF-8");
//        response.getWriter().println(json);
//    }
//}
