package com.web.board.user.dto;

import lombok.Getter;
import lombok.Setter;

/*
* DTO(Data Transfer Object) : 계층 간 데이터 교환이 이루어질 수 있도록 하는 객체
* Controller 같은 클라이언트 단과 직접 마주하는 계층에서는 Entity 대신 DTO를 사용해서 데이터를 교환하며
* Controller 외에도 레이어 사이에서 DTO를 사용할 수 있지만 View Controller 사이에서 데이터를 주고 받을 때 활용성이 높음
* */

@Getter
@Setter
public class UserDto {

    private int idx;
    private String id;
    private String password;
    private int grade_idx;
    private String reg_date;
    private String rc_time;
    private int del_chk;
    private String birth;
    private String name;
    private String address;

}
