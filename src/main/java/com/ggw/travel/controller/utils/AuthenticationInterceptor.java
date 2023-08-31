package com.ggw.travel.controller.utils;

import com.ggw.travel.entity.User;
import io.lettuce.core.RedisClient;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("session-id")) {
                sessionId = cookie.getValue();
            }
        }
        if (sessionId != null) {
            User user = (User)redisTemplate.opsForValue().get(sessionId);
            if (user != null) {
                System.out.println("go");
                return true;
            }
        }
        response.sendRedirect("http://localhost:8989/login.html");
        return false;
    }
}
