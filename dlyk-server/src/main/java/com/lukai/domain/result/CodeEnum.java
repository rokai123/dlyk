package com.lukai.domain.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum CodeEnum {
    OK(200, "登录成功"),
    FAIL(400, "登录失败!");
    @Getter
    @Setter
    Integer code;
    @Getter
    @Setter
    String msg;
}
