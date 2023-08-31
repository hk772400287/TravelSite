package com.ggw.travel.controller;

import com.ggw.travel.controller.utils.R;
import com.ggw.travel.entity.User;
import com.ggw.travel.service.UserService;
import com.ggw.travel.utils.ValidateImageCodeUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/getImage")
    public R getImage(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String securityCode = ValidateImageCodeUtils.getSecurityCode();
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        redisTemplate.opsForValue().set(timeStamp, securityCode, Duration.ofMinutes(2));
        BufferedImage image = ValidateImageCodeUtils.createImage(securityCode);
        response.setContentType("image/png");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        String imageString = Base64Utils.encodeToString(bos.toByteArray());
        Map<String, String> result = new HashMap<>();
        result.put("getTimeStamp", timeStamp);
        result.put("image", imageString);
        return new R(true, result);
    }

    @PostMapping("/register")
    public R register(HttpServletRequest request, String code, String timeStamp, @RequestBody User user) {
        String realCode = (String) redisTemplate.opsForValue().get(timeStamp);
        if (code.equalsIgnoreCase(realCode)) {
            userService.register(user);
            return new R(true, "Register Succeed");
        } else {
            return new R(false, "Wrong Security Code");
        }
    }

    @PostMapping("/login")
    public R login(@RequestBody User user, HttpServletResponse response) {
        User userDB = userService.login(user);
        String sessionId = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(sessionId, userDB, Duration.ofHours(1));
        response.setHeader("session-id", sessionId);
        return new R(true, userDB, "Log in successful");
    }

    @DeleteMapping("/logout")
    public R logout(HttpServletRequest request) {
        String sessionId = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("session-id")) {
                sessionId = cookie.getValue();
            }
        }
        redisTemplate.delete(sessionId);
        return new R(true, "Log out successful");
    }


}
