package com.lukai.domain.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum CodeEnum {
    OK(200, "操作成功"),
    FAIL(400, "操作失败!");
    @Getter
    @Setter
    Integer code;
    @Getter
    @Setter
    String msg;
}
