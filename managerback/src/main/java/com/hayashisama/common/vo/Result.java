package com.hayashisama.common.vo;

import com.hayashisama.common.constNumer.reqStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: MyHistoryOfProgress
 * @ClassName Result
 * @Description:
 * @Author: HayashiSama
 * @Create: 2023-04-02 22:30
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String Msg;
    private T data;

    public static <T> Result<T> success() {
        return new Result<>(reqStatus.SUCCESS, "success", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(reqStatus.SUCCESS, "success", data);
    }

    public static <T> Result<T> success(String Msg, T data) {
        return new Result<>(reqStatus.SUCCESS, Msg, data);
    }

    public static <T> Result<T> success(String Msg) {
        return new Result<>(reqStatus.SUCCESS, Msg, null);
    }

    public static <T> Result<T> fail() {
        return new Result<>(reqStatus.ERROR, "failed", null);
    }

    public static <T> Result<T> fail(Integer code) {
        return new Result<>(code, "failed", null);
    }

    public static <T> Result<T> fail(Integer code, String Msg) {
        return new Result<>(code, Msg, null);
    }

    public static <T> Result<T> fail(String Msg) {
        return new Result<>(reqStatus.ERROR, Msg, null);
    }
}
