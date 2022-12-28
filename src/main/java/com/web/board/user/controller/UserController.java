package com.web.board.user.controller;

import com.web.board.user.dto.UserDto;
import com.web.board.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public String join(UserDto userDto){
        userService.save(userDto);
        return "redirect:/login";
    }
}
