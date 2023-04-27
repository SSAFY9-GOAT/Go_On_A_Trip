<%--
  Created by IntelliJ IDEA.
  User: leeyr
  Date: 2023/03/26
  Time: 12:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<div class="container">
  <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
    <p class="col-md-4 mb-0 text-muted">&copy; SSAFY 광주5반</p>
    <a href="${root}" class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
      <img src="${root}/assets/img/logo.png" alt="" width="40" />
    </a>
    <ul class="nav col-md-4 justify-content-end">
      <li class="nav-item">
        <a href="#" class="nav-link px-2 text-muted">이용약관</a>
      </li>
      <li class="nav-item">
        <a href="#" class="nav-link px-2 text-muted">개인정보처리방침</a>
      </li>
      <li class="nav-item">
        <a href="${root}/notion?action=list" class="nav-link px-2 text-muted">공지사항</a>
      </li>
      <li class="nav-item">
        <a href="#" class="nav-link px-2 text-muted">문의하기</a>
      </li>
    </ul>
  </footer>
</div>
<!-- end footer -->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"
></script>
<%--header 컨트롤--%>
<script>
  document.querySelector("#btn-mv-join").addEventListener("click",function (){
    location.href = "${root}/member?action=mvregister";
  });
  document.querySelector("#btn-mv-login").addEventListener("click",function (){
    console.log("로그인 클릭")
    location.href = "${root}/account?action=mvlogin";
  });
</script>
<%--<script src="./assets/js/common.js"></script>--%>