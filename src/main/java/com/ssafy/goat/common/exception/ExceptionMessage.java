package com.ssafy.goat.common.exception;

public abstract class ExceptionMessage {

    public static final String LOGIN_EXCEPTION = "아이디나 비밀번호를 바르게 입력해주세요.";
    public static final String ACCOUNT_EXCEPTION = "등록되지 않은 계정입니다.";

    public static final String ARTICLE_EXCEPTION = "게시물을 등록하지 못했습니다.";
    public static final String NOT_FOUND_ARTICLE = "등록되지 않은 게시물입니다.";
    public static final String ARTICLE_MEMBER_DISCREPANCY = "게시물의 작성자가 아닙니다.";
}
