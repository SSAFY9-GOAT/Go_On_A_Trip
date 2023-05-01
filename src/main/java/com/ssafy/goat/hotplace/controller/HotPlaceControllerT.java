package com.ssafy.goat.hotplace.controller;

import com.ssafy.goat.common.FileStore;
import com.ssafy.goat.hotplace.UploadFile;
import com.ssafy.goat.hotplace.dto.HotPlaceDetailDto;
import com.ssafy.goat.hotplace.dto.HotPlaceDto;
import com.ssafy.goat.hotplace.dto.HotPlaceListDto;
import com.ssafy.goat.hotplace.dto.HotPlaceSearch;
import com.ssafy.goat.hotplace.service.HotPlaceService;
import com.ssafy.goat.hotplace.service.HotPlaceServiceImpl;
import com.ssafy.goat.member.dto.LoginMember;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

import static com.ssafy.goat.common.Message.EXPIRE_SESSION;
import static com.ssafy.goat.common.Message.REQUEST_LOGIN;

@WebServlet("/hotPlace")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 50
)
public class HotPlaceControllerT extends HttpServlet {

    private HotPlaceService hotPlaceService;

//    @Override
//    public void init() throws ServletException {
//        hotPlaceService = HotPlaceServiceImpl.getHotPlaceService();
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "list":
                doList(request, response);
                break;
            case "mvwrite":
                doMvwrite(request, response);
                break;
            case "write":
                doWrite(request, response);
                break;
            case "detail":
                doDetail(request, response);
                break;
            case "mvedit":
                doMvedit(request, response);
                break;
            case "edit":
                doEdit(request, response);
                break;
            case "remove":
                doRemove(request, response);
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
        String name = request.getParameter("name") == null ? "" : request.getParameter("name");
        int select = Integer.parseInt(request.getParameter("select") == null ? "1" : request.getParameter("select"));

        HotPlaceSearch hotPlaceSearch = HotPlaceSearch.builder()
                .name(name)
                .sortCondition(select)
                .build();

        List<HotPlaceListDto> hotPlaces = hotPlaceService.searchHotPlaces(hotPlaceSearch);

        request.setAttribute("hotPlaces", hotPlaces);

        forward(request, response, "/hotplace/hotplaceList.jsp");
    }

    private void doMvwrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        if (loginMember == null) {
            request.setAttribute("msg", REQUEST_LOGIN);
            forward(request, response, "/account/login.jsp");
            return;
        }
        forward(request, response, "/hotplace/addHotplace.jsp");
    }

    private void doWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        if (loginMember == null) {
            request.setAttribute("msg", EXPIRE_SESSION);
            forward(request, response, "/account/login.jsp");
            return;
        }

        String name = request.getParameter("name");
        String visitedDate = request.getParameter("visitedDate");
        int contentTypeId = Integer.parseInt(request.getParameter("contentTypeId"));
        String desc = request.getParameter("desc");
        int contentId = Integer.parseInt(request.getParameter("contentId"));

        Part part = request.getPart("hotplaceImg");

        FileStore fileStore = new FileStore();
        UploadFile uploadFile = fileStore.storeFile(part);

        HotPlaceDto hotPlaceDto = HotPlaceDto.builder()
                .name(name)
                .visitedDate(visitedDate)
                .contentTypeId(contentTypeId)
                .desc(desc)
                .uploadFile(uploadFile)
                .build();

        int result = hotPlaceService.addHotPlace(loginMember.getId(), contentId, hotPlaceDto);

        redirect(request, response, "/hotPlace?action=list");
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        if (loginMember == null) {
            request.setAttribute("msg", REQUEST_LOGIN);
            forward(request, response, "/account/login.jsp");
            return;
        }

        Long hotPlaceId = Long.parseLong(request.getParameter("hotPlaceId"));

        HotPlaceDetailDto hotPlace = hotPlaceService.searchHotPlace(hotPlaceId);
        hotPlaceService.updateHit(hotPlaceId);

        request.setAttribute("hotPlace", hotPlace);

        forward(request, response, "/hotplace/viewHotplace.jsp");
    }

    private void doMvedit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        if (loginMember == null) {
            request.setAttribute("msg", REQUEST_LOGIN);
            forward(request, response, "/account/login.jsp");
            return;
        }

        Long hotPlaceId = Long.parseLong(request.getParameter("hotPlaceId"));

        HotPlaceDetailDto hotPlace = hotPlaceService.searchHotPlace(hotPlaceId);

        request.setAttribute("hotPlace", hotPlace);

        forward(request, response, "/hotplace/editHotplace.jsp");
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        if (loginMember == null) {
            request.setAttribute("msg", EXPIRE_SESSION);
            forward(request, response, "/account/login.jsp");
            return;
        }

        Long hotPlaceId = Long.parseLong(request.getParameter("hotPlaceId"));
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        String visitedDate = request.getParameter("visitedDate");

        HotPlaceDto editHotPlace = HotPlaceDto.builder()
                .name(name)
                .desc(desc)
                .visitedDate(visitedDate)
                .build();

        int result = hotPlaceService.editHotPlace(loginMember.getId(), hotPlaceId, editHotPlace);

        redirect(request, response, "/hotPlace?action=detail&hotPlaceId=" + hotPlaceId);
    }

    private void doRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        if (loginMember == null) {
            request.setAttribute("msg", "로그인 후 사용해주세요.");
            forward(request, response, "/account/login.jsp");
            return;
        }

        Long hotPlaceId = Long.parseLong(request.getParameter("hotPlaceId"));

        int result = hotPlaceService.removeHotPlace(hotPlaceId, loginMember.getId());

        redirect(request, response, "/hotPlace?action=list");
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        response.sendRedirect(request.getContextPath() + path);
    }
}
