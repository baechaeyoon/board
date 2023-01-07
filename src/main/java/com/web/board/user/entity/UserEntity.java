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

/*
* Entity : DB의 테이블과 1:1로 직접 매핑되며, 테이블이 가지지 않는 컬럼을 필드로 가져서는 안된다
* @Setter 메서드의 사용을 지양해야 한다 -> 변경되지 않느 인스턴스에 대해서도 setter로 접근 가능하기 때문이다
* 따라서 @Setter 대신 Builder와 Constructor(생성자)를 사용
* @Builder를 사용하면 멤버 변수가 많아지더라도 어떤 값을 어떤 필드에 넣는지 코드를 통해 확인할 수 있고,
* 필요한 값만 넣는 것이 가능하다는 장점이 있다
* */

/*
* @NoArgsConstructor
*  1) 파라미터가 없는 생성자를 생성
*  2) 필드들이 final로 생성되어 있는 경우에는 필드를 초기화할 수 없기 때문에 생성자를 만들 수 없고 에러가 발생
* */
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

    @Column(name = "grade_idx")
    private int grade_idx;

    @Column(name = "reg_date")
    private String reg_date;

    @Column(name = "rc_time")
    private String rc_time;

    @Column(name = "del_chk")
    private int del_chk;

    @Column(name = "birth")
    private String birth;

    @Builder
    public UserEntity(Long idx, String id, String password, String name, int grade_idx, String reg_date, String rc_time, int del_chk, String birth) {
        this.idx = idx;
        this.id = id;
        this.password = password;
        this.name = name;
        this.grade_idx = grade_idx;
        this.reg_date = reg_date;
        this.rc_time = rc_time;
        this.del_chk = del_chk;
        this.birth = birth;
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
