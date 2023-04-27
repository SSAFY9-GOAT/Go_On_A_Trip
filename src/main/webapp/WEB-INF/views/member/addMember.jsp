<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="/common/head.jsp" %>
  <link href="${root}/assets/css/common.member.css" rel="stylesheet"/>
  <link href="${root}/assets/css/register.css" rel="stylesheet"/>
</head>

<body>
<!-- start header -->
<header class="border-bottom py-3 mb-4">
  <%@include file="/common/header.jsp" %>
</header>
<!-- end header -->

<!-- start section -->
<form id="container" method='post' action="${root}/member?action=register">
  <h2>회원 가입</h2>
  <div class="input">
    <div class="label">
      <label>아이디</label>
      <p>영문, 숫자, 4~20자</p>
    </div>
    <input type="text" name="loginId" maxlength="20" placeholder="아이디" autocomplete="off" required>
    <div class="caution"></div>
  </div>
  <div class="input">
    <div class="label">
      <label>비밀번호</label>
      <p>영문, 숫자, 8~20자</p>
    </div>
    <input type="password" name="loginPw" maxlength="20" placeholder="비밀번호" required>
    <div class="caution"></div>
    <input type="password" name="checkLoginPw" maxlength="20" placeholder="비밀번호 확인" required>
    <div class="caution"></div>
  </div>
  <div class="input">
    <div class="label">
      <label>이름</label>
    </div>
    <input type="text" name="username" maxlength="20" placeholder="이름" autocomplete="off" required>
    <div class="caution"></div>
  </div>
  <div class="input">
    <div class="label">
      <label>이메일</label>
      <p>아이디/비밀번호 찾기에 필요</p>
    </div>
    <input type="email" name="email" placeholder="이메일" autocomplete="off" required>
    <div class="caution"></div>
  </div>
  <div class="input">
    <div class="label">
      <label>휴대폰번호</label>
      <p>아이디/비밀번호 찾기에 필요</p>
    </div>
    <input type="text" name="phone" maxlength="11" placeholder="휴대폰번호 (숫자만 입력)" autocomplete="off" required>
    <div class="caution"></div>
  </div>
  <div class="row">
    <div class="col">
      <div class="input">
        <div class="label">
          <label>주민등록번호 앞자리</label>
        </div>
        <input type="text" name="birth" maxlength="6" placeholder="앞자리" autocomplete="off" required>
        <div class="caution"></div>
      </div>
    </div>
    <div class="col">
      <div class="input">
        <div class="label">
          <label>주민등록번호 뒷자리</label>
        </div>
        <input type="password" name="gender" maxlength="7" placeholder="뒷자리" autocomplete="off" required>
        <div class="caution"></div>
      </div>
    </div>
  </div>
  <div class="input">
    <div class="label">
      <label>닉네임</label>
      <p>커뮤니티 활동에 필요</p>
    </div>
    <input type="text" name="nickname" maxlength="10" placeholder="닉네임" autocomplete="off" required>
    <div class="caution"></div>
  </div>
  <input type="submit" value="회원가입">
</form>
<!-- end section -->

<!-- start footer -->
<%@include file="/common/footer.jsp" %>
<!-- end footer -->
</body>
</html>


