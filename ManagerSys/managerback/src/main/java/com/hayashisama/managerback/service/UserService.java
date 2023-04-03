package com.hayashisama.managerback.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hayashisama.managerback.entity.User;

import java.util.Map;

public interface UserService extends IService<User> {

    Map<String, Object> login(User user);
}
