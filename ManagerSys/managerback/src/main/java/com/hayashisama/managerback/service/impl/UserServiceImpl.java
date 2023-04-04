package com.hayashisama.managerback.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hayashisama.managerback.entity.User;
import com.hayashisama.managerback.mapper.UserMapper;
import com.hayashisama.managerback.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;
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
            log.info("login方法输出的key值" + key);
            // 返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("Token", key);
            System.out.println(key);
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        // 根据token从redis获取用户信息
        Object obj = redisTemplate.opsForValue().get(token);
        if (obj != null) {
            User loginUser = JSON.parseObject(JSON.toJSONString(obj), User.class);
            Map<String, Object> data = new HashMap<>();
            data.put("name", loginUser.getUsername());
            data.put("avatar", loginUser.getAvatar());
        }
        return null;
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }
}
