package com.hayashisama.managerback.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: managerback
 * @ClassName R
 * @Description: 通用结果类，服务端响应的数据最终都会封装成为此对象
 * @Author: HayashiSama
 * @Create: 2023-03-16 18:04
 * @Version 1.0
 **/

@Data
public class R<T> {
    private Integer code; // 状态码： 1成功，0和其他数字为失败
    private String msg; // 错误信息
    private T data; // 数据
    private Map map = new HashMap<>(); // 动态数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R<T> r = new R<>();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
