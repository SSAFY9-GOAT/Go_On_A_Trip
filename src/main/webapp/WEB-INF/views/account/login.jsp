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
    <form id="login-form" method="post" action="${root}/account?action=login">
      <img class="mb-4" src="${root}/assets/img/logo.png" alt="" width="280" height="280"/>
      <h1 class="h3 mb-3 fw-normal">로그인</h1>

      <div class="form-floating">
        <input
          type="text"
          class="form-control"
          id="userId"
          name="userId"
          placeholder="userId"
        />
        <label for="userId">아이디</label>
      </div>
      <div class="form-floating">
        <input
          type="password"
          class="form-control"
          id="userPassword"
          name="userPassword"
          placeholder="userPassword"
        />
        <label for="userPassword">비밀번호</label>
      </div>

      <div class="checkbox mb-3">
        <label> <input type="checkbox" value="remember-me"/> Remember me </label>
      </div>
      <button id="btn-login" class="w-100 mb-3 btn btn-lg btn-primary"
              type="submit">로그인
      </button>
      <button
        type="button"
        class="w-100 mb-3 btn btn-sm btn-outline-warning"
        data-bs-toggle="modal"
        data-bs-target="#findIdModal"
      >
        아이디 찾기
      </button>
      <button
        type="button"
        class="w-100 mb-3 btn btn-sm btn-outline-warning"
        data-bs-toggle="modal"
        data-bs-target="#findPasswordModal"
      >
        비밀번호 찾기
      </button>

    </form>
    <div class="pt-3">
      <a class="register" href="${root}/member?action=mvregister"
      >아직 계정이 없으신가요?<br/>계정 만들기</a
      >
    </div>
  </main>
</div>
<!-- end section -->

<!-- start footer -->
<%@include file="/common/footer.jsp" %>
<!-- end footer -->

<!-- 비밀번호 찾기 Modal -->
<div
  class="modal fade"
  id="findPasswordModal"
  tabindex="-1"
  aria-labelledby="findPasswordModalLabel"
  aria-hidden="true"
>
  <div class="modal-dialog modal-dialog-centered">
    <%--        <form id="" >--%>
    <form id="find-pw-form" class="modal-content" method="post" action="${root}/account?action=findPw">
      <div class="modal-header">
        <h5 class="modal-title" id="findPasswordModalLabel">비밀번호 찾기</h5>
        <button
          type="button"
          class="btn-close"
          data-bs-dismiss="modal"
          aria-label="Close"
        ></button>
      </div>
      <div class="modal-body m-3">
        <div class="row">
          <div class="col-12">
            <div class="m-3 ps-3">아이디</div>
            <div class="m-3 mb-5 col-10 m-auto">
              <input
                id="findPwId"
                name="findPwId"
                class="form-control form-control-lg"
                type="text"
                placeholder="아이디"
              />
            </div>
            <div class="m-3 ps-3">전화번호</div>
            <div class="m-3 col-10 m-auto">
              <input
                id="findPwPhone"
                name="findPwPhone"
                class="form-control form-control-lg"
                type="text"
                placeholder="01012345678"
              />
            </div>
            <div class="m-3 ps-3">이메일</div>
            <div class="m-3 col-10 m-auto">
              <input
                id="findPwEmail"
                name="findPwEmail"
                class="form-control form-control-lg"
                type="text"
                placeholder="이메일"
              />
            </div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        <button id="btn-find-pw"
                type="submit"
                class="btn btn-primary"
                data-bs-target="#showPasswordModal"
                data-bs-toggle="modal"
                data-bs-dismiss="modal"
        >
          찾기
        </button>
      </div>
    </form>
  </div>
</div>

<!-- 아이디 찾기 Modal -->
<div
  class="modal fade"
  id="findIdModal"
  tabindex="-1"
  aria-labelledby="findIdModalLabel"
  aria-hidden="true"
>
  <div class="modal-dialog modal-dialog-centered">
    <form id="find-id-form" class="modal-content" method="post" action="${root}/account?action=findId">
      <div class="modal-header">
        <h5 class="modal-title" id="findIdModalLabel">아이디 찾기</h5>
        <button
          type="button"
          class="btn-close"
          data-bs-dismiss="modal"
          aria-label="Close"
        ></button>
      </div>
      <div class="modal-body m-3">
        <div class="row">
          <div class="col-12">
            <div class="m-3 ps-3">전화번호</div>
            <div class="m-3 col-10 m-auto">
              <input
                id="findIdPhone"
                name="findIdPhone"
                class="form-control form-control-lg"
                type="text"
                placeholder="01012345678"
              />
            </div>
            <div class="m-3 ps-3">이메일</div>
            <div class="m-3 col-10 m-auto">
              <input
                id="findIdEmail"
                name="findIdEmail"
                class="form-control form-control-lg"
                type="text"
                placeholder="이메일"
              />
            </div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        <button id="btn-find-id"
                type="submit"
                class="btn btn-primary"
                data-bs-target="#showPasswordModal"
                data-bs-toggle="modal"
                data-bs-dismiss="modal"
        >
          찾기
        </button>
      </div>
    </form>
  </div>
</div>

<script>
    document.querySelector("#btn-find-pw").addEventListener("click", function () {
        let form = document.querySelector("#find-pw-form");
        form.setAttribute("action", "${root}/account?action=findPw");
        form.submit();
    });
    document.querySelector("#btn-find-id").addEventListener("click", function () {
        let form = document.querySelector("#find-id-form");
        form.setAttribute("action", "${root}/account?action=findId");
        form.submit();
    });
</script>
</body>
</html>


