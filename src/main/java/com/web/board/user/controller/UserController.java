package com.web.board.user.controller;

import com.web.board.user.dto.UserDto;
import com.web.board.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class UserController {

<<<<<<< HEAD
    //final 키워드 삭제
    private UserService userService;
=======
    private final UserService userService;
>>>>>>> parent of 39bb506 (final 키워드 삭제)

    @GetMapping(value = "/login")
    public String login(){
        return "user/login";
    }

    @PostMapping("/join")
    public String join(UserDto userDto){    //회원 추가
        userService.save(userDto);
        return "redirect:/login";
    }

    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}
