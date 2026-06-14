package com.lukai.domain.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应结果对象
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {
    private Integer code;//状态码
    private String message;//响应信息
    private Object data;//响应数据
}
