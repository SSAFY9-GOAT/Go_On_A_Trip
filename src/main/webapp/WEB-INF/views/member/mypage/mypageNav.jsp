<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3 sidebar-sticky">
        <ul class="nav flex-column">
            <li class="nav-item">
                <c:if test="${currShow eq 'myPage'}">
                <a class="nav-link active" aria-current="page" href="">
                    </c:if>
                    <c:if test="${currShow != 'myPage'}">
                    <a class="nav-link " aria-current="page" href="${root}/member?action=view">
                        </c:if>
                        <span data-feather="home" class="align-text-bottom"></span>
                        <i class="bi bi-person"></i>
                        마이페이지
                    </a>
            </li>
            <li class="nav-item ">
                <c:if test="${currShow eq 'myArticle'}">
                <a class="nav-link active" aria-current="page" href="#">
                    </c:if>
                    <c:if test="${currShow != 'myArticle'}">
                    <a class="nav-link " aria-current="page" href="${root}/member?action=myArticle">
                        </c:if>
                        <span data-feather="file" class="align-text-bottom"></span>
                        내가 쓴 게시물
                    </a>
            </li>
            <li class="nav-item">
                <c:if test="${currShow eq 'myHotplace'}">
                <a class="nav-link active" aria-current="page" href="">
                    </c:if>
                    <c:if test="${currShow != 'myHotplace'}">
                    <a class="nav-link " aria-current="page"
                       href="${root}/member?action=myHotplace">
                        </c:if>
                        <span data-feather="shopping-cart" class="align-text-bottom"></span>
                        내가 등록한 핫플레이스
                    </a>
            </li>
            <li class="nav-item">
                <c:if test="${currShow eq 'myFavorite'}">
                <a class="nav-link active" aria-current="page" href="">
                    </c:if>
                    <c:if test="${currShow != 'myFavorite'}">
                    <a class="nav-link " aria-current="page"
                       href="${root}/member?action=mvMyFavorite">
                        </c:if>
                        <span data-feather="shopping-cart" class="align-text-bottom"></span>
                        내가 좋아요 누른 핫플레이스
                    </a>
            </li>
            <li class="nav-item mt-5">
                <c:if test="${currShow eq 'modifyPw'}">
                <a class="nav-link active" aria-current="page" href="">
                    </c:if>
                    <c:if test="${currShow != 'modifyPw'}">
                    <a class="nav-link " aria-current="page"
                       href="${root}/member?action=mvModifyPw">
                        </c:if>
                        <span data-feather="home" class="align-text-bottom"></span>
                        <i class="bi bi-person"></i>
                        비밀번호 변경
                    </a>
            </li>
            <li class="nav-item">
                <c:if test="${currShow eq 'modifyNickname'}">
                <a class="nav-link active" aria-current="page" href="">
                    </c:if>
                    <c:if test="${currShow != 'modifyNickname'}">
                    <a class="nav-link " aria-current="page"
                       href="${root}/member?action=mvModifyNickname">
                        </c:if>
                        <span data-feather="home" class="align-text-bottom"></span>
                        <i class="bi bi-person"></i>
                        별명 변경
                    </a>
            </li>
            <li class="nav-item">
                <c:if test="${currShow eq 'modifyEmail'}">
                <a class="nav-link active" aria-current="page" href="">
                    </c:if>
                    <c:if test="${currShow != 'modifyEmail'}">
                    <a class="nav-link " aria-current="page"
                       href="${root}/member?action=mvModifyEmail">
                        </c:if>
                        <span data-feather="home" class="align-text-bottom"></span>
                        <i class="bi bi-person"></i>
                        이메일 변경
                    </a>
            </li>
            <li class="nav-item">
                <c:if test="${currShow eq 'modifyTel'}">
                <a class="nav-link active" aria-current="page" href="">
                    </c:if>
                    <c:if test="${currShow != 'modifyTel'}">
                    <a class="nav-link " aria-current="page"
                       href="${root}/member?action=mvModifyTel">
                        </c:if>
                        <span data-feather="home" class="align-text-bottom"></span>
                        <i class="bi bi-person"></i>
                        전화번호 변경
                    </a>
            </li>

            <li class="nav-item mt-5">
                <a class="nav-link text-danger" href="${root}/member?action=mvwithdrawal">
                    <span data-feather="shopping-cart" class="align-text-bottom"></span>
                    회원탈퇴
                </a>
            </li>
        </ul>
    </div>
</nav>