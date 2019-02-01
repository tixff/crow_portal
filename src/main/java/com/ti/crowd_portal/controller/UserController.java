package com.ti.crowd_portal.controller;

import com.ti.crowd_manager.domain.User;
import com.ti.crowd_manager.domain.result.ResultData;
import com.ti.crowd_portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        String encode  = UUID.nameUUIDFromBytes(user.getPassword().getBytes()).toString();
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
    public ResultData login(User user) {
        ResultData resultData = ResultData.createResultData();
        String encode  = UUID.nameUUIDFromBytes(user.getPassword().getBytes()).toString();
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
}
