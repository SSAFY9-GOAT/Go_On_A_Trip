package com.ssafy.goat.common;

import trend.dto.TrendViewDto;
import trend.service.TrendService;
import trend.service.TrendServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class IndexController extends HttpServlet {

    private TrendService trendService;

    @Override
    public void init() throws ServletException {
        trendService = TrendServiceImpl.getTrendService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TrendViewDto teenage = trendService.popularByTeenage();
        TrendViewDto twenty = trendService.popularByTwenty();
        TrendViewDto thirty = trendService.popularByThirty();
        TrendViewDto male = trendService.popularByMale();
        TrendViewDto female = trendService.popularByFemale();

        request.setAttribute("teenage", teenage);
        request.setAttribute("twenty", twenty);
        request.setAttribute("thirty", thirty);
        request.setAttribute("male", male);
        request.setAttribute("female", female);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
