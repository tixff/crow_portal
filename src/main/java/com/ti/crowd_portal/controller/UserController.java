package com.ti.crowd_portal.controller;

import com.ti.crowd_manager.domain.User;
import com.ti.crowd_manager.domain.result.ResultData;
import com.ti.crowd_portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author Ti
 * @date 2019/2/1
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResultData register(User user) {
        String encode = UUID.nameUUIDFromBytes(user.getPassword().getBytes()).toString();
        user.setPassword(encode);
        ResultData resultData = ResultData.createResultData();
        Integer count = userService.addUser(user);
        if (count > 0) {
            resultData.setMessage("注册成功");
            resultData.setStatus(ResultData.SUCCESS);
            return resultData;
        } else {
            resultData.setMessage("用户名重复");
            resultData.setStatus(ResultData.FAIL);
            return resultData;
        }
    }

    @PostMapping("login")
    public ResultData login(HttpServletResponse response, User user) {
        Cookie info = new Cookie("user", user.getName());
        info.setPath("/");
        response.addCookie(info);
        ResultData resultData = ResultData.createResultData();
        String encode = UUID.nameUUIDFromBytes(user.getPassword().getBytes()).toString();
        user.setPassword(encode);
        User login = userService.login(user);
        if (login != null) {
            resultData.setMessage("登录成功");
            resultData.setStatus(ResultData.SUCCESS);
            return resultData;
        } else {
            resultData.setMessage("登录失败");
            resultData.setStatus(ResultData.FAIL);
            return resultData;
        }
    }

    @GetMapping("loginout")
    public ModelAndView loginout(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("user".equals(c.getName())) {
                    c.setValue(null);
                    c.setMaxAge(0);
                    c.setPath("/");
                    response.addCookie(c);
                }
            }
        }
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
