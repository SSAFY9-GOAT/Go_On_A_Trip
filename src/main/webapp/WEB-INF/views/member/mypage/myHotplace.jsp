<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../../common/head.jsp" %>
</head>
</head>
<body>
<!-- start header -->
<header class="border-bottom py-3 mb-4">
    <%@include file="../../common/header.jsp" %>
</header>
<!-- end header -->

<%-- start section --%>
<main class="p-3 mb-3 border-bottom container-sm">
    <div class="container-sm">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-sm-start">
            <div class="row row-cols-1 row-cols-md-4 g-4">
                <c:forEach items="${hotPlaces}" var="hotPlace">
                    <div class="col-4" style="width: 300px">
                        <div class="card h-100">
                            <div class="position-relative" >
                                <img src="${root}/assets/img/userUpload/${hotPlace.storeFileName}" class="card-img-top"
                                     alt="..." style="width: 100%; height: 300px; object-fit:cover;">

                            </div>
<%--                            <img src="${root}/assets/img/userUpload/${hotPlace.storeFileName}" class="card-img-top" alt="...">--%>
                            <div class="card-body">
                                <h5 class="card-title">${hotPlace.name}</h5>
                                <p class="card-text text-truncate">${hotPlace.desc}</p>
                                <div class="text-end">
                                    조회수 : ${hotPlace.hit} | 작성자 : ${hotPlace.nickname}
                                </div>
                                <a type="button" class="btn btn-primary" href="/hotPlace/${hotPlace.hotPlaceId}">더보기</a>
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
<%@include file="../../common/footer.jsp" %>
<!-- end footer -->

</body>
</html>
