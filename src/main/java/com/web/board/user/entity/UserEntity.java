package com.web.board.user.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class UserEntity implements UserDetails {

    @Id
    @Column(name="idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

//    @Column(name = "grade_idx")
//    private int grade_idx;
//
//    @Column(name = "reg_date")
//    private String reg_date;
//
//    @Column(name = "rc_time")
//    private String rc_time;
//
//    @Column(name = "del_chk")
//    private int del_chk;
//
//    @Column(name = "birth")
//    private String birth;

    @Builder
    public UserEntity(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    //사용자의 권한을 콜렉션 형태로 반환
    //단, 클래스 자료형은 GrantedAuthority를 구현해야 함
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for(String role : name.split(",")){
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    //사용자의 password를 반환
    @Override
    public String getPassword() {
        return password;
    }

    //사용자의 id를 반환(unique한 값)
    @Override
    public String getUsername() {
        return id;
    }

    //계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        //만료되었는지 확인하는 로직
        return true;    //true -> 만료되지 않았음
    }

    //계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        //계정이 잠금되었는지 확인하는 로직
        return true;    //true -> 잠금되지 않았음
    }

    //비밀번호의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        //비밀번호가 만료되었는지 확인하는 로직
        return true;    //true -> 만료되지 않았음
    }

    //계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        //계정이 사용 가능한지 확인하는 로직
        return true;   //true -> 사용 가능
    }
}
