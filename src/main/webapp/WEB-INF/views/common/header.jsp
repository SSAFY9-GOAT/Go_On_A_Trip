<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="d-flex flex-wrap justify-content-center container">
  <a href="${root}/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
    <img src="${root}/assets/img/logo/logo_1x1.png" width="100" class="me-3"/>
    <span class="fs-4">여행을 떠나요</span>
  </a>

  <ul class="nav nav-pills">
    <li>
      <a href="${root}/attraction/search" class="nav-link px-2 link-dark">관광지조회</a>
    </li>
    <li>
      <a href="${root}/article/list" class="nav-link px-2 link-dark">자유게시판</a>
    </li>
    <li>
      <a href="${root}/hotPlace/list" class="nav-link px-2 link-dark">핫플레이스</a>
    </li>
    <li>
      <a href="${root}/tripPlan/list" class="nav-link px-2 link-dark">여행계획</a>
    </li>
    <li>
      <a href="${root}/notion/list" class="nav-link px-2 link-dark">공지사항</a>
    </li>

    <c:choose>
      <c:when test="${empty userinfo}">
        <li class="nav-item">
          <a href="${root}/account/login" id="btn-mv-login" class="nav-link link-dark px-2">로그인</a>
        </li>
        <li class="nav-item">
          <a href="${root}/register" id="btn-mv-join" class="nav-link link-dark px-2">회원가입</a>
        </li>
      </c:when>
      <c:otherwise>
        <li class="nav-item">
          <a id="btn-mypage" href="${root}/member?action=view" class="nav-link link-dark px-2">마이페이지</a>
        </li>
        <li class="nav-item">
          <a id="btn-logout" href="${root}/account?action=logout" class="nav-link link-dark px-2">로그아웃</a>
        </li>
      </c:otherwise>
    </c:choose>
  </ul>
</div>