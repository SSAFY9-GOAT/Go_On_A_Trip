//package com.ssafy.goat.article.controller;
//
//
//import com.ssafy.goat.article.dto.ArticleDetailDto;
//import com.ssafy.goat.article.dto.ArticleDto;
//import com.ssafy.goat.article.dto.ArticleListDto;
//import com.ssafy.goat.article.dto.ArticleSearch;
//import com.ssafy.goat.article.service.ArticleService;
//import com.ssafy.goat.article.service.ArticleServiceImpl;
//import com.ssafy.goat.common.Page;
//import com.ssafy.goat.common.validation.ArticleValidation;
//import com.ssafy.goat.common.validation.dto.ArticleRequest;
//import com.ssafy.goat.common.validation.dto.InvalidResponse;
//import com.ssafy.goat.member.dto.LoginMember;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//
////@WebServlet("/article")
//public class ArticleServletController extends HttpServlet {
//
//    private ArticleService articleService;
//
//    @Override
//    public void init() throws ServletException {
////        articleService = ArticleServiceImpl.getArticleService();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        switch (action) {
//            case "mvwrite":
//                doMvWriter(request, response);
//                break;
//            case "write":
//                doWriter(request, response);
//                break;
//            case "list":
//                doList(request, response);
//                break;
//            case "detail":
//                doDetail(request, response);
//                break;
//            case "mvedit":
//                doMvedit(request, response);
//                break;
//            case "edit":
//                doEdit(request, response);
//                break;
//            case "remove":
//                doRemove(request, response);
//                break;
//            default:
//                forward(request, response, "/error/ready.jsp");
//                break;
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        doGet(request, response);
//    }
//
//    private void doMvWriter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
//        if (loginMember == null) {
//            request.setAttribute("msg", "로그인 후 사용해주세요.");
//            forward(request, response, "/account/login.jsp");
//            return;
//        }
//        forward(request, response, "/article/addArticle.jsp");
//    }
//
//    private void doWriter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
//        if (loginMember == null) {
//            request.setAttribute("msg", "세션이 만료되었습니다. 다시 로그인 해주세요.");
//            forward(request, response, "/account/login.jsp");
//            return;
//        }
//
//        String title = request.getParameter("title");
//        String content = request.getParameter("content");
//
//        ArticleValidation articleValidation = new ArticleValidation();
//        ArticleRequest articleRequest = ArticleRequest.builder()
//                .title(title)
//                .content(content)
//                .build();
//
//        List<InvalidResponse> validate = articleValidation.validate(articleRequest);
//        if (!validate.isEmpty()) {
//            request.setAttribute("msg", "올바르지 않은 데이터입니다.");
//            forward(request, response, "/article/addArticle.jsp");
//            return;
//        }
//
//        ArticleDto articleDto = ArticleDto.builder()
//                .title(title)
//                .content(content)
//                .build();
//
//        int result = articleService.addArticle(loginMember.getId(), articleDto);
//
//        response.sendRedirect(request.getContextPath() + "/article?action=list");
//    }
//
//    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String condition = request.getParameter("condition") == null ? "" : request.getParameter("condition");
//        int sortCondition = Integer.parseInt(request.getParameter("sortCondition") == null ? "1" : request.getParameter("sortCondition"));
//
//        int pageNum = 1;
//        int amount = 10;
//
//        if (request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
//            pageNum = Integer.parseInt(request.getParameter("pageNum"));
//            amount = Integer.parseInt(request.getParameter("amount"));
//        }
//
//        ArticleSearch articleSearch = ArticleSearch.builder()
//                .condition(condition)
//                .sortCondition(sortCondition)
//                .build();
//
//        List<ArticleListDto> articles = articleService.searchArticles(articleSearch, pageNum, amount);
//        int totalCount = articleService.getTotalCount();
//        Page page = new Page(pageNum, amount, totalCount);
//
//        request.setAttribute("page", page);
//        request.setAttribute("articles", articles);
//        forward(request, response, "/article/articleList.jsp");
//    }
//
//    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
//        if (loginMember == null) {
//            request.setAttribute("msg", "회원 전용 서비스입니다. 로그인 후 사용해주세요.");
//            forward(request, response, "/account/login.jsp");
//            return;
//        }
//
//        Long articleId = Long.parseLong(request.getParameter("articleId"));
//
//        int result = articleService.increaseHit(articleId);
//        ArticleDetailDto article = articleService.searchArticle(articleId);
//
//        request.setAttribute("article", article);
//        request.setAttribute("isMine", article.getMemberId().equals(loginMember.getId()));
//        forward(request, response, "/article/viewArticle.jsp");
//    }
//
//    private void doMvedit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Long articleId = Long.parseLong(request.getParameter("articleId"));
//
//        ArticleDetailDto article = articleService.searchArticle(articleId);
//
//        request.setAttribute("article", article);
//        forward(request, response, "/article/editArticle.jsp");
//    }
//
//    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
//        if (loginMember == null) {
//            return;
//        }
//
//        Long articleId = Long.parseLong(request.getParameter("articleId"));
//        String title = request.getParameter("title");
//        String content = request.getParameter("content");
//
//        ArticleValidation articleValidation = new ArticleValidation();
//
//        ArticleRequest articleRequest = ArticleRequest.builder()
//                .title(title)
//                .content(content)
//                .build();
//
//        List<InvalidResponse> validate = articleValidation.validate(articleRequest);
//        if (!validate.isEmpty()) {
//            return;
//        }
//
//        ArticleDto articleDto = ArticleDto.builder()
//                .title(title)
//                .content(content)
//                .build();
//
//        int result = articleService.editArticle(articleId, loginMember.getId(), articleDto);
//        redirect(request, response, "/article?action=detail&articleId=" + articleId);
//    }
//
//    private void doRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
//        if (loginMember == null) {
//            return;
//        }
//
//        Long articleId = Long.parseLong(request.getParameter("articleId"));
//
//        int result = articleService.removeArticle(articleId, loginMember.getId());
//        redirect(request, response, "/article?action=list");
//    }
//
//    private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
//        dispatcher.forward(request, response);
//    }
//
//    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
//        response.sendRedirect(request.getContextPath() + path);
//    }
//}
