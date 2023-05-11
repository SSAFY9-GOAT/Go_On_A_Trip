package com.ssafy.goat.common;

import com.ssafy.goat.trend.dto.TrendViewDto;
import com.ssafy.goat.trend.service.TrendService;
import com.ssafy.goat.trend.service.TrendServiceImpl;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
//@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController extends HttpServlet {

    private final TrendService trendService;

//    @Override
//    public void init() throws ServletException {
//        trendService = TrendServiceImpl.getTrendService();
//    }

//    @GetMapping
    public String goIndex(Model model){
        TrendViewDto teenage = trendService.popularByTeenage();
        TrendViewDto twenty = trendService.popularByTwenty();
        TrendViewDto thirty = trendService.popularByThirty();
        TrendViewDto male = trendService.popularByMale();
        TrendViewDto female = trendService.popularByFemale();

        model.addAttribute("teenage", teenage);
        model.addAttribute("twenty", twenty);
        model.addAttribute("thirty", thirty);
        model.addAttribute("male", male);
        model.addAttribute("female", female);

        return "index";
    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        TrendViewDto teenage = trendService.popularByTeenage();
//        TrendViewDto twenty = trendService.popularByTwenty();
//        TrendViewDto thirty = trendService.popularByThirty();
//        TrendViewDto male = trendService.popularByMale();
//        TrendViewDto female = trendService.popularByFemale();
//
//        request.setAttribute("teenage", teenage);
//        request.setAttribute("twenty", twenty);
//        request.setAttribute("thirty", thirty);
//        request.setAttribute("male", male);
//        request.setAttribute("female", female);
//
//        request.getRequestDispatcher("/index.jsp").forward(request, response);
//    }
}
