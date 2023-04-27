<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="/common/head.jsp" %>
  <style>
      .wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
      .wrap * {padding: 0;margin: 0;}
      .wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
      .wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
      .info .title {padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;}
      .info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
      .info .close:hover {cursor: pointer;}
      .info .body {position: relative;overflow: hidden;}
      .info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
      .desc .ellipsis {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
      .desc .jibun {font-size: 11px;color: #888;margin-top: -2px;}
      .info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
      .info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
      .info .link {color: #5085BB;}
  </style>
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
  <div class="p-4 p-md-5 mb-4 text-secondary rounded bg-white shadow">
    <div class="col-md-6 px-0">
      <h1 class="display-5 fst-italic">핫플 인증</h1>
      <p class="lead my-3">방문했던 핫플레이스를 자랑하세요!</p>
    </div>
  </div>
  <!-- start album -->

  <div class="row">
    <div class="col text-end">
      <form class="row mb-3">
        <div class="input-group mb-3">
          <input type="text" id="attractionName" class="form-control" placeholder="관광지 검색" aria-label="관광지 검색"
                 aria-describedby="button-addon2"/>
          <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal"
                  onclick="searchAttraction()">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search"
                 viewBox="0 0 16 16">
              <path
                  d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
            </svg>
          </button>
        </div>
      </form>
      <div id="map" style="width: 100%; height: 500px"></div>
    </div>
    <form class="col col-6" action="${root}/hotPlace?action=write" method="post" enctype="multipart/form-data">
      <input type="hidden" id='contentId' name="contentId" value="-1">
      <input type="hidden" id='contentTypeId' name="contentTypeId" value="-1">
      <div class="mb-3">
        <label for="uploadFileName" class="form-label">인증샷</label>
        <input name="hotplaceImg" type="file" class="form-control" id="uploadFileName">
      </div>
      <div class="mb-3">
        <label for="name" class="form-label">나만의 핫플레이스 이름</label>
        <input type="text" class="form-control" id="name" name="name">
      </div>
      <div class="mb-3">
        <label for="visitedDate" class="form-label">방문 날짜</label>
        <input type="date" class="form-control" id="visitedDate" name="visitedDate">
      </div>
      <div class="mb-3">
        <label for="desc" class="form-label">핫플레이스 설명</label>
        <textarea class="form-control" id="desc" rows="3" name="desc"></textarea>
      </div>
      <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-3">
        <button class="btn btn-primary me-md-2" type="submit">글쓰기</button>
        <button class="btn btn-secondary" type="button">목록</button>
      </div>
    </form>
  </div>
  <!-- end album -->
</main>
<!-- end section -->
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">검색결과</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="attraction"><%-- 비동기 통신 --%></div>
    </div>
  </div>
</div>
<%-- end section --%>

<!-- start footer -->
<%@include file="/common/footer.jsp" %>
<!-- end footer -->
<script type="text/javascript" src="${root}/assets/js/requestApi.js"></script>
<script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=92031818da3bea1d2a0cd22686ab48ea">
</script>
<script>
    var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스

    var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
        level: 3, //지도의 레벨(확대, 축소 정도)
    };

    var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

    function selectAttraction(id, contentTypeId, title, addr1, zipcode, firstImage, latitude, longitude) {
        document.getElementById("contentId").setAttribute('value', id);
        document.getElementById("contentTypeId").setAttribute('value', contentTypeId);
        // 지도에 마커를 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: new kakao.maps.LatLng(latitude, longitude)
        });
        console.log(title);
        // 커스텀 오버레이에 표시할 컨텐츠 입니다
        // 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
        // 별도의 이벤트 메소드를 제공하지 않습니다
        var content = '<div class="wrap">' +
            '    <div class="info">' +
            '        <div class="title">' +
            title +
            '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' +
            '        </div>' +
            '        <div class="body">' +
            '            <div class="img">' +
            '                <img src="' + firstImage + '" width="73" height="70">' +
            '           </div>' +
            '            <div class="desc">' +
            '                <div class="ellipsis">' + addr1 + '</div>' +
            '                <div class="jibun ellipsis">(우) ' + zipcode + '</div>' +
            '            </div>' +
            '        </div>' +
            '    </div>' +
            '</div>';

        var overlay = new kakao.maps.CustomOverlay({
            content: content,
            map: map,
            position: marker.getPosition()
        });

        overlay.setMap(map);
        setCenter(latitude, longitude);
    }

    function setCenter(latitude, longitude) {
        // 이동할 위도 경도 위치를 생성합니다
        var moveLatLon = new kakao.maps.LatLng(latitude, longitude);

        // 지도 중심을 이동 시킵니다
        map.setCenter(moveLatLon);
    }
</script>
</body>
</html>
