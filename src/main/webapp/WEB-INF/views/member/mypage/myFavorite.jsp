<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/common/head.jsp" %>
</head>
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
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-sm-start">
            <div class="row row-cols-1 row-cols-md-4 g-4">
                <c:forEach items="${favorites}" var="favorite">
                    <div class="col-4" style="width: 300px">
                        <div class="card h-100">
                            <img src="${root}/assets/store/${favorite.storeFileName}"
                                 class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">${favorite.name}</h5>
                                <p class="card-text text-truncate">${favorite.desc}</p>
                                <div class="text-end">
                                    조회수 : ${favorite.hit} | 작성자 : ${favorite.nickname}
                                </div>
                                <button type="button" class="btn btn-primary"
                                        onclick="location.href='/hotPlace?action=detail&hotPlaceId=${favorite.hotPlaceId}'">
                                    더보기
                                </button>
                            </div>
                            <div class="card-footer">
                                <small class="text-body-secondary">${favorite.createdDate}</small>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <%-- 페이징 시작 --%>
        <div class="d-flex justify-content-center mt-3">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <%-- 이전버튼시작 --%>
                    <c:if test="${page.prev}">
                        <li class="page-item">
                            <a class="page-link"
                               href="${root}/member?action=myFavorite&pageNum=${page.startPage-1}&amount=${page.amount}">이전</a>
                        </li>
                    </c:if>
                    <%-- 이전버튼종료 --%>
                    <%-- 페이징번호 처리시작 --%>
                    <c:forEach var="num" begin="${page.startPage}" end="${page.endPage}">
                        <li class="page-item">
                            <a class="page-link"
                               href="${root}/member?action=myFavorite&pageNum=${num}&amount=${page.amount}">${num}</a>
                        </li>
                    </c:forEach>
                    <%-- 페이징번호 처리종료 --%>
                    <%-- 시작버튼시작 --%>
                    <c:if test="${page.next}">
                        <li class="page-item">
                            <a class="page-link"
                               href="${root}/member?action=myFavorite&pageNum=${page.endPage + 1}&amount=${page.amount}">다음</a>
                        </li>
                    </c:if>
                    <%-- 시작버튼종료 --%>
                </ul>
            </nav>
        </div>
        <%-- 페이징 종료 --%>

    </div>
</main>
<%-- end section --%>

<!-- start footer -->
<%@include file="/common/footer.jsp" %>
<!-- end footer -->

</body>
</html>
