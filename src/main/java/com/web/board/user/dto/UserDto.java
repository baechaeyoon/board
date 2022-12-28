package com.web.board.user.dto;

import lombok.Getter;
import lombok.Setter;

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

}
