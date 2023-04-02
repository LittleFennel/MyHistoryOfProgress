package com.hayashisama.managerback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hayashisama.managerback.entity.User;
import com.hayashisama.managerback.mapper.UserMapper;
import com.hayashisama.managerback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.hayashisama.common.constNumer.redisTime.USER_LOGIN_OUTTIME;

/**
 * @program: managerback
 * @ClassName UserServiceImpl
 * @Description:
 * @Author: HayashiSama
 * @Create: 2023-03-16 17:51
 * @Version 1.0
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;
    /*
    * 根据用户名和密码查询用户是否合法
    * */
    @Override
    public Map<String, Object> login(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        wrapper.eq(User::getPassword, user.getPassword());
        User loginUser = this.baseMapper.selectOne(wrapper);
        if (loginUser != null) {
            // 生成 Token，暂时用 UUID，终极方案是 JWT
            String key = "user:" + UUID.randomUUID();
            // 存入 redis
            loginUser.setPassword(null);
            redisTemplate.opsForValue().set(key, loginUser, USER_LOGIN_OUTTIME, TimeUnit.MINUTES);
            // 返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("Token", key);
            System.out.println(key);
            return data;
        }
        return null;
    }
}
