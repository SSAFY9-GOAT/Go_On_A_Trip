<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="/common/head.jsp" %>
</head>
<body>
<!-- start header -->
<header class="border-bottom py-3 mb-4">
  <%@include file="/common/header.jsp" %>
</header>
<!-- end header -->

<%-- start section --%>
<main class="p-3 mb-3 border-bottom container-sm">
  <div class="container-sm">
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
      <form class="row g-3" method="get" action="${root}/hotPlace?action=list">
        <input type="hidden" name="action" value="list">
        <div class="col-auto">
          <input type="text" class="form-control" id="name" name="name" placeholder="작성자, 제목, 내용">
        </div>
        <div class="col-auto">
          <select class="form-select" name="select">
            <option value="1" selected>최신등록순</option>
            <option value="2">보기순</option>
            <option value="3">추천순</option>
          </select>
        </div>
        <div class="col-auto">
          <button type="submit" class="btn btn-secondary mb-3">검색</button>
          <button type="button" class="btn btn-primary mb-3" onclick="location.href='/hotPlace?action=mvwrite'">글쓰기</button>
        </div>
      </form>
    </div>
    <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-sm-start">
      <div class="row row-cols-1 row-cols-md-4 g-4">
        <c:forEach items="${hotPlaces}" var="hotPlace">
          <div class="col-4" style="width: 300px">
            <div class="card h-100">
              <img src="${root}/assets/store/${hotPlace.storeFileName}" class="card-img-top" alt="...">
              <div class="card-body">
                <h5 class="card-title">${hotPlace.name}</h5>
                <p class="card-text text-truncate">${hotPlace.desc}</p>
                <div class="text-end">
                  조회수 : ${hotPlace.hit} | 작성자 : ${hotPlace.nickname}
                </div>
                <button type="button" class="btn btn-primary" onclick="location.href='/hotPlace?action=detail&hotPlaceId=${hotPlace.hotPlaceId}'">더보기</button>
                <button type="button" class="btn btn-primary" onclick="location.href='/hotPlace?action=mvedit&hotPlaceId=${hotPlace.hotPlaceId}'">수정</button>
                <button type="button" class="btn btn-primary" onclick="location.href='/member?action=favorite&hotPlaceId=${hotPlace.hotPlaceId}'">담기</button>
              </div>
              <div class="card-footer">
                <small class="text-body-secondary">${hotPlace.createdDate}</small>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
  </div>
</main>
<%-- end section --%>

<!-- start footer -->
<%@include file="/common/footer.jsp" %>
<!-- end footer -->
</body>
</html>
