package com.xw.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    /**
     * 获取当前登录用户信息的三种方法
     * spring会自动注入
     * @param userDetails
     * @return
     */
    @GetMapping("/user1")
    public Object getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    @GetMapping("/user2")
    public Object getUserInfo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/user3")
    public Object getUserInfo(Authentication authentication) {
        return authentication;
    }
}
