package com.web.board.security;

import com.web.board.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity  //spring security를 활성화한다는 의미의 어노테이션
@RequiredArgsConstructor
public class securityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;  //사용자 정보를 가져올 클래스스
    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring().antMatchers("/static/css/**","/static/js/**","/static/img/**");
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	/*
        http
                .authorizeHttpRequests()    //접근에 대한 인증 설정
                .antMatchers("/index","/login").permitAll()  //누구나 접근 허용
                .antMatchers("/").hasRole("welcome")    //welcome과 root만 접근 가능
                .antMatchers("/admin").hasRole("master")    //master만 접근 가능
                .anyRequest().authenticated()   // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능

                .and()
                    .formLogin()
                        .loginPage("/login")    //로그인 페이지 링크
                        .defaultSuccessUrl("/main")     //로그인 성공 후 리다이렉트 주소

                .and()
                    .logout()
                        .logoutSuccessUrl("/login")     //로그아웃 성공 시 리다이렉트 주소
                        .invalidateHttpSession(true)    //세션 날리기
        ;
        */
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                //해당 서비스(userService)에서는 UserDetailsService를 implements해서 loadUserByUsername() 구현해야 함
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
