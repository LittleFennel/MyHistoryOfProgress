package com.hayashisama.managerback.controller;

import com.hayashisama.common.constNumer.reqStatus;
import com.hayashisama.common.vo.Result;
import com.hayashisama.managerback.entity.User;
import com.hayashisama.managerback.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: managerback
 * @ClassName UserController
 * @Description:
 * @Author: HayashiSama
 * @Create: 2023-03-15 23:06
 * @Version 1.0
 **/
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public Result<List<User>> getAllUsers() {
        List<User> userList = userService.list();
        return Result.success("查询非常成功", userList);
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody User user) {
        Map<String, Object> data = userService.login(user);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail(reqStatus.FAIL_ERROR_PARAMS, "用户名或密码错误！");
    }

    @GetMapping("/info")
    public Result<Map<String, Object>> getUserInfo(@RequestParam("token") String token) {
        // 根据 token 获取用户信息
        Map<String, Object> data = userService.getUserInfo(token);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail(reqStatus.FAIL_NONEXISTENT_USER, "登录信息无效，请重新登录！");
    }

}
