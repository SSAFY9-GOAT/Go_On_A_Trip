package com.ssafy.goat.member.controller;

import com.ssafy.goat.article.dto.ArticleListDto;
import com.ssafy.goat.article.service.ArticleService;
import com.ssafy.goat.common.Page;
import com.ssafy.goat.common.exception.SignUpException;
import com.ssafy.goat.common.validation.SignUpValidation;
import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.common.validation.dto.MemberRequest;
import com.ssafy.goat.hotplace.dto.HotPlaceListDto;
import com.ssafy.goat.hotplace.service.HotPlaceService;
import com.ssafy.goat.member.dto.LoginMember;
import com.ssafy.goat.member.dto.MemberAddDto;
import com.ssafy.goat.member.dto.MemberDto;
import com.ssafy.goat.member.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//@WebServlet("/member")
public class MemberControllerOld extends HttpServlet {

  private MemberService memberService;
  private ArticleService articleService;

  private HotPlaceService hotPlaceService;

  @Override
  public void init() {
//    memberService = MemberServiceImpl.getMemberService();
//    articleService = ArticleServiceImpl.getArticleService();
//    hotPlaceService = HotPlaceServiceImpl.getHotPlaceService();

  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String action = request.getParameter("action");
    String path = "";
    switch (action) {
//      case "register":
//        doRegister(request, response);
//        break;
//      case "mvregister":
//        forward(request, response, "/member/addMember.jsp");
//        break;
//      case "view":
//        path = viewMypage(request, response);
//        forward(request, response, path);
//        break;
      case "myArticle":
        path = mvMyArticle(request, response);
        forward(request, response, path);
        break;
//      case "mvMyFavorite":
//        path = mvMyFavorite(request, response);
//        forward(request, response, path);
//        break;
//      case "favorite":
//        doFavorite(request, response);
//        break;
      case "myHotplace":
        path = mvMyHotplace(request, response);
        forward(request, response, path);
        break;
//      case "modifyPw":
//        path = modifyPw(request, response);
//        forward(request, response, path);
//        break;
//      case "mvModifyPw":
//        path = mvModifyPw(request, response);
//        forward(request, response, path);
//        break;
//      case "modifyNickname":
//        path = modifyNickname(request, response);
//        redirect(request, response, path);
//        break;
//      case "mvModifyNickname":
//        path = mvModifyNickname(request, response);
//        forward(request, response, path);
//        break;
      case "modifyEmail":
        path = modifyEmail(request, response);
        redirect(request, response, path);
        break;
      case "mvModifyEmail":
        path = mvModifyEmail(request, response);
        forward(request, response, path);
        break;
      case "ModifyTel":
        path = modifyTel(request, response);
        redirect(request, response, path);
        break;
      case "mvModifyTel":
        path = mvModifyTel(request, response);
        forward(request, response, path);
        break;
//      case "mvwithdrawal":
//        path = mvwithdrawal(request, response);
//        forward(request, response, path);
//        break;
      case "withdrawal":
        doWithdrawal(request, response);
        break;
    }
  }

  private String mvwithdrawal(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    session.setAttribute("currShow", "deleteMember");
    return "/member/mypage.jsp";
  }

  private String mvMyHotplace(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
    Long memberId = loginMember.getId();
    List<HotPlaceListDto> hotPlaces = hotPlaceService.searchHotPlaces(memberId);

    request.setAttribute("hotPlaces", hotPlaces);
    return "/member/mypage/myHotplace.jsp";
  }

  public void doFavorite(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    HttpSession session = request.getSession();
    LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
    Long memberId = loginMember.getId();
    Long hotPlaceId = Long.parseLong(request.getParameter("hotPlaceId"));
    hotPlaceService.doFavorite(memberId, hotPlaceId); //insert

    String path = "/member?action=mvMyFavorite";

    include(request, response, path);

  }

  private String mvMyArticle(HttpServletRequest request, HttpServletResponse response) {
    String condition =
        request.getParameter("condition") == null ? "" : request.getParameter("condition");
    int sortCondition = Integer.parseInt(request.getParameter("sortCondition") == null ? "1"
        : request.getParameter("sortCondition"));

    int pageNum = 1;
    int amount = 10;

    if (request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
      pageNum = Integer.parseInt(request.getParameter("pageNum"));
      amount = Integer.parseInt(request.getParameter("amount"));
    }

    HttpSession session = request.getSession();
    LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
    Long memberId = loginMember.getId();

    List<ArticleListDto> articles = articleService.searchMyArticles(memberId, pageNum, amount);
    int totalCount = articleService.getTotalCount();
    Page page = new Page(pageNum, amount, totalCount);

    request.setAttribute("page", page);
    request.setAttribute("articles", articles);

    session.setAttribute("currShow", "myArticle");
    return "/member/mypage/myArticle.jsp";
  }


  private String mvMyFavorite(HttpServletRequest request, HttpServletResponse response) {

    int pageNum = 1;
    int amount = 10;

    if (request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
      pageNum = Integer.parseInt(request.getParameter("pageNum"));
      amount = Integer.parseInt(request.getParameter("amount"));
    }

    HttpSession session = request.getSession();
    LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
    Long memberId = loginMember.getId();

    List<HotPlaceListDto> favorites = hotPlaceService.searchFavorites(memberId, pageNum, amount);
    int totalCount = favorites.size();
    Page page = new Page(pageNum, amount, totalCount);

    request.setAttribute("page", page);
    request.setAttribute("favorites", favorites);

    session.setAttribute("currShow", "favorite");
    return "/member/mypage/myFavorite.jsp";
  }


  private String mvModifyTel(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    session.setAttribute("currShow", "modifyTel");
    return "/member/mypage.jsp";
  }

  private String modifyTel(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");

    String currTel = request.getParameter("currTel");
    String newTel = request.getParameter("newTel");
    String pwCheck = request.getParameter("pwCheck");

    if (!pwCheck.equals(loginMember.getLoginPw())) {
      request.setAttribute("msg", "비밀번호가 틀렸습니다.");
      return "/member/mypage.jsp";
    }
    if (currTel.equals(newTel)) {
      request.setAttribute("msg", "기존 이메일 같습니다.");
      return "/member/mypage.jsp";
    }

    memberService.changePhone(loginMember.getId(), newTel);
    request.setAttribute("msg", "전화번호 변경이 완료되었습니다.");
    return "/member?action=view";
  }

  private String modifyEmail(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");

    String currEmail = request.getParameter("currEmail");
    String newEmail = request.getParameter("newEmail");
    String pwCheck = request.getParameter("pwCheck");

    if (!pwCheck.equals(loginMember.getLoginPw())) {
      request.setAttribute("msg", "비밀번호가 틀렸습니다.");
      return "/member/mypage.jsp";
    }
    if (currEmail.equals(newEmail)) {
      request.setAttribute("msg", "기존 이메일 같습니다.");
      return "/member/mypage.jsp";
    }

    memberService.changeEmail(loginMember.getId(), newEmail);
    request.setAttribute("msg", "이메일 변경이 완료되었습니다.");
    return "/member?action=view";
  }

  private String mvModifyEmail(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    session.setAttribute("currShow", "modifyEmail");
    return "/member/mypage.jsp";
  }

//  private String mvModifyNickname(HttpServletRequest request, HttpServletResponse response) {
//    HttpSession session = request.getSession();
//    session.setAttribute("currShow", "modifyNickname");
//    return "/member/mypage.jsp";
//  }

  private String modifyNickname(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");

    String currNickname = request.getParameter("currNickname");
    String newNickname = request.getParameter("newNickname");
    String pwCheck = request.getParameter("pwCheck");

    if (!pwCheck.equals(loginMember.getLoginPw())) {
      request.setAttribute("msg", "비밀번호가 틀렸습니다.");
      forward(request, response, "/member/mypage.jsp");
    }
    if (currNickname.equals(newNickname)) {
      request.setAttribute("msg", "기존 닉네임과 같습니다.");
      forward(request, response, "/member/mypage.jsp");
    }

    memberService.changeNickname(loginMember.getId(), newNickname);
    request.setAttribute("msg", "닉네임 변경이 완료되었습니다. ");
    session.setAttribute("currShow", "myPage");
    return "/member?action=view";

  }

//  private String mvModifyPw(HttpServletRequest request, HttpServletResponse response) {
//    HttpSession session = request.getSession();
//    session.setAttribute("currShow", "modifyPw");
//    return "/member/mypage.jsp";
//  }


//  private String modifyPw(HttpServletRequest request, HttpServletResponse response) {
//    HttpSession session = request.getSession();
//    LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
//
//    String currPw = request.getParameter("currPw");
//    String newPw = request.getParameter("newPw");
//    String newPwCheck = request.getParameter("newPwCheck");
//
//    if (!currPw.equals(loginMember.getLoginPw())) {
//      request.setAttribute("msg", "비밀번호가 틀렸습니다.");
//      return "/member/mypage.jsp";
//    }
//    if (!newPw.equals(newPwCheck)) {
//      request.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
//      return "/member/mypage.jsp";
//    }
//    if (currPw.equals(newPw)) {
//      request.setAttribute("msg", "기존 비밀번호와 같습니다.");
//      return "/member/mypage.jsp";
//    }
//
//    memberService.changePassword(loginMember.getId(), newPw);
//    request.setAttribute("msg", "비밀번호 변경이 완료되었습니다. 다시 로그인 하세요.");
//    return "/account?action=logout";
//  }

//  private String viewMypage(HttpServletRequest request, HttpServletResponse response) {
//    HttpSession session = request.getSession();
//    LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
//
//    MemberDto dto = memberService.myPage(loginMember.getId());
//    String birth1 = dto.getBirth().substring(0, 2);
//    String birth2 = dto.getBirth().substring(2, 4);
//    String birth3 = dto.getBirth().substring(4, 6);
//    if (Integer.parseInt(dto.getGender()) > 2) {
//      dto.setBirth("20" + birth1 + "년 " + birth2 + "월 " + birth3 + "일");
//    } else {
//      dto.setBirth("19" + birth1 + "년 " + birth2 + "월 " + birth3 + "일");
//    }
//
//    if (Integer.parseInt(dto.getGender()) % 2 == 0) {
//      dto.setGender("여성");
//    } else {
//      dto.setGender("남성");
//    }
//
//    session.setAttribute("currShow", "myPage");
//    session.setAttribute("loginUserDto", dto);
//    return "/member/mypage.jsp";
//  }


  private void doRegister(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String loginId = request.getParameter("memberId");
    String loginPw = request.getParameter("memberPassword");
    String username = request.getParameter("memberName");
    String email = request.getParameter("memberEmail");
    String phone = request.getParameter("memberPhone");
    String nickname = request.getParameter("memberNickname");
    String birth = request.getParameter("memberBirth");
    String gender = request.getParameter("memberGender").substring(0, 1);

    SignUpValidation validation = new SignUpValidation();
    MemberRequest memberRequest = MemberRequest.builder()
        .loginId(loginId)
        .loginPw(loginPw)
        .username(username)
        .email(email)
        .phone(phone)
        .nickname(nickname)
        .birth(birth)
        .gender(gender)
        .build();
    List<InvalidResponse> responses = validation.validate(memberRequest);

    if (!responses.isEmpty()) {
      throw new SignUpException();
    }

    MemberAddDto memberAddDto = MemberAddDto.builder()
        .loginId(loginId)
        .loginPw(loginPw)
        .username(username)
        .email(email)
        .phone(phone)
        .nickname(nickname)
        .birth(birth)
        .gender(gender)
        .build();
    memberService.signUp(memberAddDto);

    response.sendRedirect(request.getContextPath() + "/account/login.jsp");
  }

  private void doWithdrawal(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
    if (loginMember == null) {
      response.sendRedirect("/");
      return;
    }

    String loginPw = request.getParameter("pw");

    memberService.withdrawal(loginMember.getId(), loginPw);

    response.sendRedirect("/account?action=logout");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");
    doGet(request, response);
  }

  private void forward(HttpServletRequest request, HttpServletResponse response, String path)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher(path);
    dispatcher.forward(request, response);
  }

  private void redirect(HttpServletRequest request, HttpServletResponse response, String path)
      throws IOException {
    response.sendRedirect(request.getContextPath() + path);
  }


  private void include(HttpServletRequest request, HttpServletResponse response, String path)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher(path);
    response.setContentType("text/html; charset=UTF-8");
    dispatcher.include(request, response);
  }
}
