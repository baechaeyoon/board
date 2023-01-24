package com.web.board.security;

import lombok.Getter;

/*
* spring security가 제공하는 ROLE 네이밍 정책이 ROLE_권한 이므로 맞춰서 작성
* */

@Getter
public enum Role {
    // welcome(1), friend(2), family(3), master(4)
    ROLE_welcome("가입자"), ROLE_friend("일반 사용자"), ROLE_family("상위 사용자"), ROLE_master("관리자");

    private String description;

    Role(String description){
        this.description = description;
    }
}
