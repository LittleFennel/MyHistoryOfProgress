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

    @Test
    public void testString() {
        // redisTemplate.opsForValue().set("name", "熊昆");

        Object name = redisTemplate.opsForValue().get("user:d415c4a6-f24a-4d3a-a1df-6426463eed7b");

        System.out.println("name: " + name);
    }

    @Test
    public void testUserMapper() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }
}
