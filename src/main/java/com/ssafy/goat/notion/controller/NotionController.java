package com.ssafy.goat.notion.controller;

import com.ssafy.goat.common.Page;
import com.ssafy.goat.member.dto.LoginMember;
import com.ssafy.goat.notion.dto.NotionDto;
import com.ssafy.goat.notion.service.NotionService;
import com.ssafy.goat.notion.service.NotionServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/notion")
public class NotionController extends HttpServlet {

    private NotionService notionService;

    @Override
    public void init() {
        notionService = NotionServiceImpl.getNotionService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "list":
                doList(request, response);
                break;
            case "mvwrite":
                doMvWriter(request, response);
                break;
            case "write":
                doWriter(request, response);
                break;
            case "view":
                doView(request, response);
                break;
            case "mvmodify":
                doMvmodify(request, response);
                break;
            case "modify":
                doModify(request, response);
                break;
            case "remove":
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        if (loginMember == null) {
            request.setAttribute("authority", null);
        } else {
            request.setAttribute("authority", loginMember.getAuthority());
        }

        int pageNum = 1;
        int amount = 10;

        if (request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
            amount = Integer.parseInt(request.getParameter("amount"));
        }

        List<NotionDto> topNotions = notionService.searchTopNotions();
        List<NotionDto> notions = notionService.searchNotions(pageNum, amount);
        int totalCount = notionService.getTotalCount();
        Page page = new Page(pageNum, amount, totalCount);

        request.setAttribute("page", page);
        request.setAttribute("topNotions", topNotions);
        request.setAttribute("notions", notions);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/notion/notionList.jsp");
        dispatcher.forward(request, response);
    }

    private void doMvWriter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/notion/addNotion.jsp");
        dispatcher.forward(request, response);
    }

    private void doWriter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }

        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");

        String title = request.getParameter("title");
        String content = request.getParameter("content");

        NotionDto notionDto = NotionDto.builder()
                .title(title)
                .content(content)
                .build();

        int result = notionService.addNotion(loginMember.getId(), notionDto);
        if (result == 0) {
            return;
        }
        response.sendRedirect(request.getContextPath() + "/notion?action=list");
    }

    private void doView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        if (loginMember == null) {
            request.setAttribute("authority", null);
        } else {
            request.setAttribute("authority", loginMember.getAuthority());
        }

        Long notionId = Long.parseLong(request.getParameter("notionId"));

        NotionDto notion = notionService.searchNotion(notionId);

        request.setAttribute("notion", notion);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/notion/viewNotion.jsp");
        dispatcher.forward(request, response);
    }

    private void doMvmodify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long notionId = Long.parseLong(request.getParameter("notionId"));

        NotionDto notion = notionService.searchNotion(notionId);

        request.setAttribute("notion", notion);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/notion/editNotion.jsp");
        dispatcher.forward(request, response);
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long notionId = Long.parseLong(request.getParameter("notionId"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        NotionDto notionDto = NotionDto.builder()
                .id(notionId)
                .title(title)
                .content(content)
                .build();

        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");

        int count = notionService.editNotion(notionId, loginMember.getId(), notionDto);

        response.sendRedirect(request.getContextPath() + "/notion?action=view&notionId=" + notionId);
    }
}
