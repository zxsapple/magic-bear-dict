package com.magic.bear.dict.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zxs
 * @date 2022/8/15 10:46
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {
    Integer code;

    T data;

    String msg;

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(0, data, "");
    }

    public static <T> ResultVO<T> success() {
        return new ResultVO<>(0, null, "");
    }

    public static <T> ResultVO<T> fail() {
        return new ResultVO<>(405, null, "业务错误");
    }

    public static <T> ResultVO<T> unexpectedFail() {
        return new ResultVO<>(500, null, "未知异常");
    }
}
