<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <link href="/assets/css/travelplan.css" rel="stylesheet">
  <%@ include file="/common/head.jsp" %>
</head>

<body>
<!-- start header -->
<header class="border-bottom py-3 mb-4">
  <%@include file="/common/header.jsp" %>
</header>
<!-- end header -->

<!-- start section -->
<div class="container">
  <main class="container">
    <div class="p-4 p-md-5 mb-4 text-secondary rounded bg-white shadow">
      <div class="col-md-6 px-0">
        <h1 class="display-5 fst-italic">나만의 여행 계획 세우기</h1>
        <p class="lead my-3">나만의 여행 계획을 세워보세요!</p>
      </div>
    </div>
    <!-- start section -->

    <!-- start album -->

    <div class="row">
      <div id="map" class='col-8 mh-100'>
        <script type="text/javascript"
                src="//dapi.kakao.com/v2/maps/sdk.js?appkey=92031818da3bea1d2a0cd22686ab48ea"></script>
      </div>
      <div class="col-4">
        <form id="planDetail" method="post" action="${root}/tripPlan?action=deletePlan&tripPlanId=${tripPlan.tripPlanId}">
          <input type="hidden" id="planId" name="planId" value="${tripPlan.tripPlanId}">
          <div class="input-group mb-3">
            <span class="input-group-text" >제목</span>
            <input type="text" class="form-control" name="planTitle" value="${tripPlan.title}" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" readonly>
          </div>
          <table id="plan" class="table table-hover ">
            <tr>
              <th>여행 리스트</th>

            </tr>
            <c:forEach items="${tripPlan.detailPlans}" var="detailPlan">
              <tr>
                <td>${detailPlan.title}</td>
              </tr>
            </c:forEach>

          </table>
          <button id="removePlan" type='submit' class='btn btn-danger' >삭제하기</button>
        </form>
      </div>
    </div>
    <div></div>


    <!-- end album -->
  </main>
</div>
<!-- end section -->

<!-- start footer -->
<%@include file="/common/footer.jsp" %>
<!-- end footer -->
<script src='/assets/js/travelplan.js'></script>
<script>
  // var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
  <c:forEach items="${tripPlan.detailPlans}" var="detailPlan">
  var x = ${detailPlan.latitude};
  var y = ${detailPlan.longitude};
    var clickPosition = new kakao.maps.LatLng(x, y);
  // 지도 클릭이벤트가 발생했는데 선을 그리고있는 상태가 아니면
  if (!drawingFlag) {

    // 상태를 true로, 선이 그리고있는 상태로 변경합니다
    drawingFlag = true;

    // 지도 위에 선이 표시되고 있다면 지도에서 제거합니다
    deleteClickLine();

    // 지도 위에 커스텀오버레이가 표시되고 있다면 지도에서 제거합니다
    deleteDistnce();

    // 지도 위에 선을 그리기 위해 클릭한 지점과 해당 지점의 거리정보가 표시되고 있다면 지도에서 제거합니다
    deleteCircleDot();

    // 클릭한 위치를 기준으로 선을 생성하고 지도위에 표시합니다
    clickLine = new kakao.maps.Polyline({
      map: map, // 선을 표시할 지도입니다
      path: [clickPosition], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
      strokeWeight: 3, // 선의 두께입니다
      strokeColor: '#db4040', // 선의 색깔입니다
      strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
      strokeStyle: 'solid' // 선의 스타일입니다
    });

    // 선이 그려지고 있을 때 마우스 움직임에 따라 선이 그려질 위치를 표시할 선을 생성합니다
    // moveLine = new kakao.maps.Polyline({
    //     strokeWeight: 3, // 선의 두께입니다
    //     strokeColor: '#db4040', // 선의 색깔입니다
    //     strokeOpacity: 0.5, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
    //     strokeStyle: 'solid' // 선의 스타일입니다
    // });

    // 클릭한 지점에 대한 정보를 지도에 표시합니다
    displayCircleDot(clickPosition, 0);


  } else { // 선이 그려지고 있는 상태이면

    // 그려지고 있는 선의 좌표 배열을 얻어옵니다
    var path = clickLine.getPath();

    // 좌표 배열에 클릭한 위치를 추가합니다
    path.push(clickPosition);

    // 다시 선에 좌표 배열을 설정하여 클릭 위치까지 선을 그리도록 설정합니다
    clickLine.setPath(path);

    var distance = Math.round(clickLine.getLength());
    displayCircleDot(clickPosition, distance);
  }
  </c:forEach>

  function setCenter(x, y) {
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = new kakao.maps.LatLng(x, y);

    // 지도 중심을 이동 시킵니다
    map.setCenter(moveLatLon);
    // 마커가 표시될 위치입니다
    var markerPosition = new kakao.maps.LatLng(x, y);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
      position: markerPosition,
    });

    map.setLevel(4);
    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);
  }
</script>

</body>
</html>

