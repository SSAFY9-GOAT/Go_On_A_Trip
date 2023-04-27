<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="/common/head.jsp" %>
  <link href="${root}/assets/css/login.css" rel="stylesheet"/>
</head>

<body>
<!-- start header -->
<header class="border-bottom py-3 mb-4">
  <%@include file="/common/header.jsp" %>
</header>
<!-- end header -->

<!-- start section -->
<div id="wrapper" class="text-center">
  <main class="form-signin w-100 m-auto">
    <div id="login-form">
      <img class="mb-4" src="${root}/assets/img/logo.png" alt="" width="280" height="280"/>
      <c:choose>
        <c:when test="${empty findPw}">
          <h1 class="h3 mb-3 fw-normal">아이디</h1>
          <div class="form-floating">
            <input
              type="text"
              class="form-control"
              id="findid"
              value="${findId}"
              placeholder="userId"
            />
            <label for="findid">아이디</label>
          </div>
        </c:when>
        <c:when test="${not empty findPw}">
          <h1 class="h3 mb-3 fw-normal">비밀번호</h1>

          <div class="form-floating">
            <input
              type="text"
              id="findpw"
              class="form-control"
              value="${findPw}"
              placeholder="userId"
            />
            <label for="findpw">비밀번호</label>
          </div>
        </c:when>
      </c:choose>
      <a class="w-100 mt-3 mb-3 btn btn-lg btn-primary"
         type="button" href="${root}/account?action=mvlogin">로그인하러가기
      </a>
    </div>
  </main>
</div>
<div class="container">
  <div class="container-fluid">
    <div class="row">
      <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 ms-5">
        <c:choose>
          <c:when test="${!findPw}">

          </c:when>
          <c:when test="${findPw}">
            <div>
              <div
                class="modal fade"
                id="showPasswordModal"
                aria-hidden="true"
                aria-labelledby="showPasswordModalLabel"
                tabindex="-1"
              >
                <div class="modal-dialog modal-dialog-centered">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="showPasswordModalLabel">비밀번호 확인</h5>
                      <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                      ></button>
                    </div>
                    <div class="modal-body input-group">
            <span class="input-group-text">비밀번호</span
            ><input type="text" id="showPw" class="form-control" placeholder="12345" readonly/>
                    </div>
                    <div class="modal-footer">
                      <button
                        class="btn btn-primary"
                        data-bs-target="#showPasswordModal"
                        data-bs-toggle="modal"
                        data-bs-dismiss="modal"
                      >
                        Back to first
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </c:when>
        </c:choose>
      </main>
    </div>
  </div>
</div>
<!-- end section -->

<!-- start footer -->
<%@include file="/common/footer.jsp" %>
<!-- end footer -->

<%-- script --%>
<script>
    document.querySelector("#btn-change-pw").addEventListener("click", function () {
        let form = document.querySelector("#change-pw-form");
        form.setAttribute("action", "${root}/member?action=modifyPw");
        form.submit();
    });

    document.querySelector("#btn-change-nickname").addEventListener("click", function () {
        let form = document.querySelector("#change-nickname-form");
        form.setAttribute("action", "${root}/member?action=modifyNickname");
        form.submit();
    });

    document.querySelector("#btn-change-email").addEventListener("click", function () {
        let form = document.querySelector("#change-email-form");
        form.setAttribute("action", "${root}/member?action=modifyEmail");
        form.submit();
    });
    document.querySelector("#btn-change-tel").addEventListener("click", function () {
        let form = document.querySelector("#change-tel-form");
        form.setAttribute("action", "${root}/member?action=modifyTel");
        form.submit();
    });
    document.querySelector("#btn-delete-member").addEventListener("click", function () {
        let form = document.querySelector("#delete-member-form");
        form.setAttribute("action", "${root}/member?action=withdrawal");
        form.submit();
    });
</script>
</body>
</html>
