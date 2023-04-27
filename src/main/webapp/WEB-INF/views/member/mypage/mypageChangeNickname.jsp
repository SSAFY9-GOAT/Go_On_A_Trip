<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<form id="change-nickname-form" method="post" class="needs-validation" action="/member?action=modifyNickname">
  <div
    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom col-md-7 col-lg-8"
  >
    <h1 class="h2">닉네임 변경</h1>
    <div class="btn-toolbar mb-2 mb-md-0 ">
      <div class="btn-group me-2">
        <button id="btn-change-nickname" type="submit" class="btn btn-sm btn-outline-secondary">변경하기</button>
      </div>
    </div>
  </div>

  <div>
    <div class="col-md-7 col-lg-8">

      <input type="hidden" name="action" value="modifyNickname">
      <div class="row g-3">
        <div class="col-12">
          <label for="currNickname" class="form-label">현재 닉네임</label>
          <div class="input-group ">
            <input type="text" class="form-control" id="currNickname" name="currNickname"
                   value="${loginUserDto.nickname}"
                   readonly>
          </div>
        </div>
        <div class="col-sm-6">
          <label for="newNickname" class="form-label">바꿀 닉네임</label>
          <input type="text" class="form-control" id="newNickname" name="newNickname" placeholder=""
                 required>
        </div>

        <div class="col-sm-6">
          <label for="pwCheck" class="form-label">비밀번호 확인</label>
          <input type="password" class="form-control" id="pwCheck" name="pwCheck" placeholder=""
                 required>
        </div>
      </div>

    </div>
  </div>
</form>