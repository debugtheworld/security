package com.xw.security.controller;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ！！！登录帐号为user
 * ！！！密码为启动时控制台输出的密码
 */
@RestController
public class LoginController {
    // 重定向策略
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    // 请求缓存（跳转过来之前会先把请求保存到session里面，所以下面请求可以直接从缓存中获取请求）
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 下面的参数都应该进行封装
     */
    @GetMapping("/login/authentication")
    public Object loginAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取缓存的请求
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        /**
         * 以html结尾的请求都跳转至登陆页面
         * 通过ajax发送的请求给出相应的提示，前端在根据提示做对应处理
         */
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, "/login.html");
            }
        }
        return "需要进行身份验证"; // 这里应该封装
    }
}
