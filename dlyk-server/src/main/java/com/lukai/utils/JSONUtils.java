package com.lukai.utils;

import com.lukai.domain.User;
import com.lukai.domain.result.R;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

public class JSONUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();//创建了单例的 ObjectMapper 实例（线程安全，可复用）
    public static String beanToJson(Object result) {
        return OBJECT_MAPPER.writeValueAsString(result);
    }

    /**
     * 将JSON字符串转换为指定类型的Java对象
     * 
     * 使用Jackson ObjectMapper将JSON字符串反序列化为指定类的实例。
     * 如果JSON格式无效或反序列化失败，将抛出运行时异常。
     *
     * @param <T> 目标对象的类型
     * @param json 待转换的JSON字符串
     * @param tClass 目标对象的Class类型，用于指定反序列化的目标类
     * @return 反序列化后的Java对象实例
     * @throws RuntimeException 当JSON解析失败或反序列化出错时抛出
     */
    public static <T> T jsonToBean(String json, Class<T> tClass) {
        try {
            return OBJECT_MAPPER.readValue(json, tClass);
        } catch (JacksonException e) {
            throw new RuntimeException(e);
        }
    }
}
