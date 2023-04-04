package com.hayashisama.managerback.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hayashisama.managerback.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService extends IService<User> {

    Map<String, Object> login(User user);

    Map<String, Object> getUserInfo(String token);

    List<User> getUserList();
}
