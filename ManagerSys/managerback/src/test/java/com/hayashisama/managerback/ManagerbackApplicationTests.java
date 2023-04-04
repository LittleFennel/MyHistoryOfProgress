package com.hayashisama.managerback;

import com.hayashisama.managerback.entity.User;
import com.hayashisama.managerback.mapper.UserMapper;
import com.hayashisama.managerback.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
class ManagerbackApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Test
    public void testString() {
        String key = "username";
        String value = "李奕诺";

        redisTemplate.opsForValue().set(key, value);

        Object name = redisTemplate.opsForValue().get("username");

        System.out.println("name: " + name);
    }

    @Test
    public void testUserMapper() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void getUserList() {
        List<User> userList = userService.getUserList();
        userList.forEach(System.out::println);
    }
}
