<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="common/head.jsp" %>
</head>

<body>
<!-- start header -->
<header class="border-bottom py-3 mb-4">
  <%@include file="common/header.jsp" %>
</header>
<!-- end header -->

<!-- start section1 -->
<div class="container">
  <div id="carouselExampleCaptions" class="carousel slide">
    <div class="carousel-indicators">
      <button
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="0"
          class="active"
          aria-current="true"
          aria-label="Slide 1"
      ></button>
      <button
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="1"
          aria-label="Slide 2"
      ></button>
      <button
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="2"
          aria-label="Slide 3"
      ></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="./assets/img/panel_01.png" class="d-block w-100 h-25" alt="..."/>
        <div class="carousel-caption d-none d-md-block">
          <h5>First slide label</h5>
          <p>Some representative placeholder content for the first slide.</p>
        </div>
      </div>
      <div class="carousel-item">
        <img src="./assets/img/panel_02.png" class="d-block w-100" alt="..."/>
        <div class="carousel-caption d-none d-md-block">
          <h5>Second slide label</h5>
          <p>Some representative placeholder content for the second slide.</p>
        </div>
      </div>
      <div class="carousel-item">
        <img src="./assets/img/panel_03.png" class="d-block w-100" alt="..."/>
        <div class="carousel-caption d-none d-md-block">
          <h5>Third slide label</h5>
          <p>Some representative placeholder content for the third slide.</p>
        </div>
      </div>
    </div>
    <button
        class="carousel-control-prev"
        type="button"
        data-bs-target="#carouselExampleCaptions"
        data-bs-slide="prev"
    >
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button
        class="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleCaptions"
        data-bs-slide="next"
    >
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
</div>
<!-- end section1 -->

<!-- start section2 -->
<div class="container px-4 py-5 text-center" id="featured-3">
  <h2>수많은 여행객을 위한<br/><strong>국내 1위 여행지 서비스 Enjoy Trip!</strong></h2>
  <div class="mt-5">
    <p>
      지역별 관광지 및 숙박 등 편리한 <strong>관광지 조회</strong>가 가능하고,<br/>맛집 등
      유용한 <strong>음식 정보</strong>를 접할 수 있으며,<br/>수많은 여행객들과 소통하는
      <strong>커뮤니티</strong>를 이용할 수 있습니다.
    </p>
  </div>
  <div class="row g-4 py-5 row-cols-1 row-cols-lg-5">
    <div class="feature col">
      <h3 class="fs-2"><strong id="register-tour">0</strong></h3>
      <p>등록된 관광지</p>
    </div>
    <div class="feature col">
      <h3 class="fs-2"><strong id="register-hotel">0</strong></h3>
      <p>등록된 숙박</p>
    </div>
    <div class="feature col">
      <h3 class="fs-2"><strong id="register-store">0</strong></h3>
      <p>등록된 맛집</p>
    </div>
    <div class="feature col">
      <h3 class="fs-2"><strong id="resgister-member">0</strong></h3>
      <p>가입한 회원</p>
    </div>
    <div class="feature col">
      <h3 class="fs-2"><strong id="write-article">0</strong></h3>
      <p>작성된 게시물</p>
    </div>
  </div>
</div>
<!-- end section2 -->

<!-- start section3 -->
<div class="container px-4 py-5 text-center" id="featured-3">
  <h2>실시간<br/><strong>인기있는 여행지</strong></h2>
  <div class="row row-cols-1 row-cols-md-5 g-4 mt-3">
    <%-- 10대 --%>
    <div class="col">
      <h3>10대 1위</h3>
      <div class="card h-100">
        <img src="${teenage.firstImage}" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">${teenage.title}</h5>
          <p class="card-text">${teenage.addr1}</p>
        </div>
      </div>
    </div>
    <%-- 20대 --%>
    <div class="col">
      <h3>20대 1위</h3>
      <div class="card h-100">
        <img src="${twenty.firstImage}" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">${twenty.title}</h5>
          <p class="card-text">${twenty.addr1}</p>
        </div>
      </div>
    </div>
    <%-- 30대 --%>
    <div class="col">
      <h3>30대 1위</h3>
      <div class="card h-100">
        <img src="${thirty.firstImage}" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">${thirty.title}</h5>
          <p class="card-text">${thirty.addr1}</p>
        </div>
      </div>
    </div>
    <%-- 남성 --%>
    <div class="col">
      <h3>남성 1위</h3>
      <div class="card h-100">
        <img src="${male.firstImage}" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">${male.title}</h5>
          <p class="card-text">${male.addr1}</p>
        </div>
      </div>
    </div>
    <%-- 여성 --%>
    <div class="col">
      <h3>여성 1위</h3>
      <div class="card h-100">
        <img src="${female.firstImage}" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">${female.title}</h5>
          <p class="card-text">${female.addr1}</p>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- end section3 -->

<!-- start footer -->
<%@include file="common/footer.jsp" %>
<!-- end footer -->
<script src="./assets/js/index.js"></script>
</body>
</html>

