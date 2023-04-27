<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div
  class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom col-md-7 col-lg-8"
>
  <h1 class="h2">마이페이지</h1>
  <div class="btn-toolbar mb-2 mb-md-0 ">
    <div class="btn-group me-2">
    </div>
  </div>
</div>

<div>
  <div class="col-md-7 col-lg-8">
    <form class="needs-validation" novalidate>
      <div class="row g-3">
        <div class="col-12">
          <label for="userId" class="form-label">아이디</label>
          <div class="input-group ">
            <input type="text" class="form-control" id="userId" name="userId" placeholder="아이디"
                   value="${loginUserDto.loginId}" readonly>
          </div>
        </div>
        <div class="col-sm-6">
          <label for="userName" class="form-label">이름</label>
          <input type="text" class="form-control" id="userName" name="userName" placeholder=""
                 value="${loginUserDto.username}"
                 readonly>
        </div>

        <div class="col-sm-6">
          <label for="userNickname" class="form-label">별명</label>
          <input type="text" class="form-control" id="userNickname" name="userNickname" placeholder=""
                 value="${loginUserDto.nickname}" readonly>
        </div>
        <div class="col-sm-6">
          <label for="userEmail" class="form-label">이메일</label>
          <input type="text" class="form-control" id="userEmail" name="userEmail" placeholder="아이디"
                 value="${loginUserDto.email}" readonly>
        </div>

        <div class="col-sm-6">
          <label for="userTel" class="form-label">전화번호</label>
          <input type="text" class="form-control" id="userTel" name="userTel" placeholder=""
                 value="${loginUserDto.phone}"
                 readonly>
          <div class="invalid-feedback">
            Valid first name is required.
          </div>
        </div>
        <div class="col-sm-6">
          <label for="userBirth" class="form-label">생년월일</label>
          <input type="text" class="form-control" id="userBirth" name="userBirth" placeholder=""
                 value="${loginUserDto.birth}"
                 readonly>
          <div class="invalid-feedback">
            Valid last name is required.
          </div>
        </div>
        <div class="col-sm-6">
          <label for="userGender" class="form-label">성별</label>
          <input type="text" class="form-control" id="userGender" name="userGender" placeholder=""
                 value="${loginUserDto.gender}"
                 readonly>
        </div>
      </div>
    </form>
  </div>
</div>

