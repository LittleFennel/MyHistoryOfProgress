package com.hayashisama.managerback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hayashisama.managerback.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: managerback
 * @ClassName UserMapper
 * @Description:
 * @Author: HayashiSama
 * @Create: 2023-03-15 22:22
 * @Version 1.0
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
