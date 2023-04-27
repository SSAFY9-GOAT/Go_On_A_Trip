<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="/common/head.jsp" %>
  <link href="${root}/assets/css/kakaomap.css" rel="stylesheet"/>
</head>
<body>
<!-- start header -->
<header class="border-bottom py-3 mb-4">
  <%@include file="/common/header.jsp" %>
</header>
<!-- end header -->

<%-- start section --%>
<!-- start section -->
<main class="container">
  <!-- start album -->
  <div class="row">
    <div class="col text-end">
      <div id="map" style="width: 100%; height: 500px"></div>
    </div>
    <div class="col col-6">
      <div class="mb-3">
        <img src="${root}/assets/store/${hotPlace.storeFileName}" class="img-fluid w-100 h-25" alt="...">
      </div>
      <div class="mb-3 row g-3">
        <div class="col-md-6">
          <label for="nickname" class="form-label">작성자</label>
          <input type="text" class="form-control" id="nickname" name="nickname" value="${hotPlace.nickname}" disabled>
        </div>
        <div class="col-md-6">
          <label for="visitedDate" class="form-label">방문 날짜</label>
          <input type="text" class="form-control" id="visitedDate" name="visitedDate" value="${hotPlace.visitedDate}" disabled>
        </div>
      </div>
      <div class="mb-3">
        <label for="name" class="form-label">핫플레이스 이름</label>
        <input type="text" class="form-control" id="name" name="name" value="${hotPlace.name}" disabled>
      </div>
      <div class="mb-3">
        <label for="desc" class="form-label">핫플레이스 설명</label>
        <textarea class="form-control" id="desc" rows="10" disabled>${hotPlace.desc}</textarea>
      </div>
      <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-3">
        <button class="btn btn-secondary" type="button">목록</button>
      </div>
      </form>
    </div>
  </div>
  <!-- end album -->
</main>

<!-- start footer -->
<%@include file="/common/footer.jsp" %>
<!-- end footer -->
<script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=92031818da3bea1d2a0cd22686ab48ea">
</script>
<script>
    var latitude = '<c:out value="${hotPlace.latitude}"/>';
    var longitude = '<c:out value="${hotPlace.longitude}"/>';

    var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
    var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(latitude, longitude), //지도의 중심좌표.
        level: 3 //지도의 레벨(확대, 축소 정도)
    };

    var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

    // 지도에 마커를 표시합니다
    var marker = new kakao.maps.Marker({
      map: map,
      position: new kakao.maps.LatLng(latitude, longitude)
    });

    var content = '<div class="wrap">' +
            '    <div class="info">' +
            '        <div class="title">${hotPlace.title}</div>' +
            '        <div class="body">' +
            '            <div class="img">' +
            '                <img src="${hotPlace.firstImage}" width="73" height="70">' +
            '           </div>' +
            '            <div class="desc">' +
            '                <div class="ellipsis">${hotPlace.addr1}</div>' +
            '                <div class="jibun ellipsis">(우) ${hotPlace.zipcode}</div>' +
            '            </div>' +
            '        </div>' +
            '    </div>' +
            '</div>';

    // 마커 위에 커스텀오버레이를 표시합니다
    // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
    var overlay = new kakao.maps.CustomOverlay({
      content: content,
      map: map,
      position: marker.getPosition()
    });

    var moveLatLon = new kakao.maps.LatLng(latitude, longitude);

    // 지도 중심을 이동 시킵니다
    map.setCenter(moveLatLon);
</script>
</body>
</html>
