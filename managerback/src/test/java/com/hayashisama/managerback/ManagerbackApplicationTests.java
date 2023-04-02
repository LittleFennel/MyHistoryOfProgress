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
    private UserMapper userMapper;

    @Test
    public void testString() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
}
