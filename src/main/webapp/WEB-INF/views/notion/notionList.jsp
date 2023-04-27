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
    <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-sm-start ">
      <table class="table m-auto wrapper">
        <thead class="table-light">
        <tr>
          <th scope="col" class=' text-center'>번호</th>
          <th scope="col" class=' text-center'>제목</th>
          <th scope="col" class=' text-center'>등록일</th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <c:forEach items="${topNotions}" var="notion">
          <tr>
            <th scope="row" class='text-center'>
              <span class="badge text-bg-primary">공지</span>
            </th>
            <td><a class='linkToNotion' href='${root}?action=view&notionId=${notion.id}'>${notion.title}</a></td>
            <td class='text-center'>${notion.createdDate}</td>
          </tr>
        </c:forEach>
        <c:forEach items="${notions}" var="notion" varStatus="status">
          <tr>
            <th scope="row" class='text-center'>${status.count}</th>
            <td><a class='linkToNotion' href='${root}?action=view&notionId=${notion.id}'>${notion.title}</a></td>
            <td class='text-center'>${notion.createdDate}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
    <%-- 페이징 시작 --%>
    <div class="d-flex justify-content-center mt-3">
      <nav aria-label="Page navigation example">
        <ul class="pagination">
          <%-- 이전버튼시작 --%>
          <c:if test="${page.prev}">
            <li class="page-item">
              <a class="page-link"
                 href="${root}/article?action=list&pageNum=${page.startPage-1}&amount=${page.amount}">이전</a>
            </li>
          </c:if>
          <%-- 이전버튼종료 --%>
          <%-- 페이징번호 처리시작 --%>
          <c:forEach var="num" begin="${page.startPage}" end="${page.endPage}">
            <li class="page-item">
              <a class="page-link" href="${root}/notion?action=list&pageNum=${num}&amount=${page.amount}">${num}</a>
            </li>
          </c:forEach>
          <%-- 페이징번호 처리종료 --%>
          <%-- 시작버튼시작 --%>
          <c:if test="${page.next}">
            <li class="page-item">
              <a class="page-link"
                 href="${root}/notion?action=list&pageNum=${page.endPage + 1}&amount=${page.amount}">다음</a>
            </li>
          </c:if>
          <%-- 시작버튼종료 --%>
        </ul>
      </nav>
    </div>
    <%-- 페이징 종료 --%>
    <c:if test="${authority eq 'ADMIN'}">
      <div class='wrapper m-auto mt-5'>
        <a href='${root}?action=mvwrite' type='button' id='btnAddNotification' class='btn btn-success'>공지등록</a>
      </div>
    </c:if>
  </div>
</main>
<%-- end section --%>

<!-- start footer -->
<%@include file="/common/footer.jsp" %>
<!-- end footer -->
</body>
</html>
