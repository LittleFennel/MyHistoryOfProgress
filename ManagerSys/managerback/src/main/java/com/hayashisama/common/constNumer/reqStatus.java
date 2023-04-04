package com.hayashisama.common.constNumer;

public class reqStatus {
    public static final Integer SUCCESS = 20000; // 查询成功状态码
    public static final Integer ERROR = 20001; // 查询错误状态码
    public static final Integer FAIL_ERROR_PARAMS = 20002; // 用户名或密码错误

    public static final Integer FAIL_NONEXISTENT_USER = 20003; // 不存在该用户
}
