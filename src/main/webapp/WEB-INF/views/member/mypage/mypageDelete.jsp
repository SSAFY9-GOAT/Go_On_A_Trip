<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<form id="delete-member-form" method="post" class="needs-validation" action="/member?action=withdrawal">
  <div
    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom col-md-7 col-lg-8"
  >
    <h1 class="h2">회원 탈퇴</h1>
    <div class="btn-toolbar mb-2 mb-md-0 ">
      <div class="btn-group me-2">
        <button id="btn-delete-member" type="submit" class="btn btn-sm btn-danger">탈퇴하기</button>
      </div>
    </div>
  </div>
  <div>
    <div class="col-md-7 col-lg-8">
      <input type="hidden" name="action" value="modifyPw">
      정말로 삭제하시겠습니까?
      <div class="row g-3">
        <div class="col-12">
          <label for="pw" class="form-label">비밀번호를 입력하세요</label>
          <div class="input-group ">
            <input type="password" class="form-control" id="pw" name="pw"
                   required>
          </div>
        </div>
      </div>

    </div>
  </div>
</form>

