<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/common/head.jsp"%>
</head>

<body>
<!-- start header -->
<header class="border-bottom py-3 mb-4">
    <%@include file="/common/header.jsp"%>
</header>
<!-- end header -->

<!-- start section -->
<div class="container">
    <main class="p-3 mb-3 border-bottom container-sm">
        <div class="container-sm">
            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <form class="row g-3" method="get" action="${root}/tripPlan?action=list">
                    <input type="hidden" name="action" value="list">
                    <div class="col-auto">
                        <input type="text" class="form-control" id="condition" name="condition" placeholder="작성자, 제목">
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-secondary mb-3">검색</button>
                        <button type="button" class="btn btn-primary mb-3" onclick="location.href='${root}?action=mvcreate'">글쓰기</button>
                    </div>
                </form>
            </div>
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-sm-start ">
                <table class="table m-auto wrapper">
                    <thead>
                    <tr>
                        <th scope="col" class=' text-center'>번호</th>
                        <th scope="col" class=' text-center'>제목</th>
                        <th scope="col" class=' text-center'>작성자</th>
                        <th scope="col" class=' text-center'>등록일</th>
                    </tr>
                    </thead>
                    <tbody class="table-group-divider">
                    <c:forEach items="${plans}" var="plan" varStatus="status">
                        <tr>
                            <th scope="row" class='text-center'>${status.count}</th>
                            <td><a class='linkToNotion' href='${root}/tripPlan?action=detail&tripPlanId=${plan.tripPlanId}'>${plan.title}</a></td>
                            <td class='text-center'>${plan.nickname}</td>
                            <td class='text-center'>${plan.createdDate}</td>
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
                                <a class="page-link" href="${root}/article?action=list&pageNum=${num}&amount=${page.amount}">${num}</a>
                            </li>
                        </c:forEach>
                        <%-- 페이징번호 처리종료 --%>
                        <%-- 시작버튼시작 --%>
                        <c:if test="${page.next}">
                            <li class="page-item">
                                <a class="page-link"
                                   href="${root}/article?action=list&pageNum=${page.endPage + 1}&amount=${page.amount}">다음</a>
                            </li>
                        </c:if>
                        <%-- 시작버튼종료 --%>
                    </ul>
                </nav>
            </div>
            <%-- 페이징 종료 --%>
        </div>
    </main>
</div>
<!-- end section -->

<!-- start footer -->
<%@include file="/common/footer.jsp"%>
<!-- end footer -->
<script src="./assets/js/index.js"></script>
</body>
</html>

