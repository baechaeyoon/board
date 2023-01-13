package com.web.board.user.controller;

import com.web.board.user.dto.UserDto;
import com.web.board.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /*
    * index 페이지
    * @author : baechaeyoon
    * @date : 2023-01-03
    * */
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }


    /*
    * 로그인 페이지
    * @author : baechaeyoon
    * @date : 2022-12-27
    * */
    @GetMapping(value = "/login")
    public String login(){
        return "user/login";
    }

    @GetMapping(value = "/join")
    public String join(){
        return "/user/join";
    }

    /*
    * 회원 추가
    * @author : baechaeyoon
    * @date : 2022-12-27
    * */
    @PostMapping("/join")
    public String join(UserDto userDto){
        userService.save(userDto);
        return "redirect:/login";
    }

    /*
    * 로그아웃
    * @author : baechaeyoon
    * @date : 2022-12-27
    * */
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
    
    @GetMapping("/main")
    public String main() {
    	return "board/main";
    }
}
