package com.web.board.security;

import lombok.Getter;

/*
* spring security가 제공하는 ROLE 네이밍 정책이 ROLE_권한 이므로 맞춰서 작성
* */

@Getter
public enum Role {
    // welcome(1), friend(2), family(3), master(4)
    welcome("ROLE_welcome"), friend("ROLE_friend"), family("ROLE_family"), master("ROLE_master");

    private String description;

    Role(String description){
        this.description = description;
    }
}
