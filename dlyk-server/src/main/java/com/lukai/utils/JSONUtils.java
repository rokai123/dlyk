package com.lukai.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();//创建了单例的 ObjectMapper 实例（线程安全，可复用）

    /**
     * 将Java对象转换为JSON字符串
     * 
     * 使用Jackson ObjectMapper将对象序列化为JSON格式字符串。
     * 如果对象为null，返回"null"字符串。
     * 如果序列化失败，将抛出运行时异常。
     *
     * @param obj 待转换的Java对象，可以为null
     * @return 序列化后的JSON字符串
     * @throws RuntimeException 当JSON序列化失败时抛出
     */
    public static String beanToJson(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("JSON序列化失败: " + e.getMessage(), e);
        }
    }

    /**
     * 将JSON字符串转换为指定类型的Java对象
     * 
     * 使用Jackson ObjectMapper将JSON字符串反序列化为指定类的实例。
     * 如果JSON格式无效、为空或反序列化失败，将抛出运行时异常。
     *
     * @param <T> 目标对象的类型
     * @param json 待转换的JSON字符串，不能为null或空
     * @param tClass 目标对象的Class类型，用于指定反序列化的目标类，不能为null
     * @return 反序列化后的Java对象实例
     * @throws RuntimeException 当JSON解析失败、输入参数无效或反序列化出错时抛出
     */
    public static <T> T jsonToBean(String json, Class<T> tClass) {
        if (json == null || json.trim().isEmpty()) {
            throw new RuntimeException("JSON字符串不能为空");
        }
        if (tClass == null) {
            throw new RuntimeException("目标类型不能为null");
        }
        
        try {
            return OBJECT_MAPPER.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON反序列化失败，目标类型: " + tClass.getName() + ", 错误信息: " + e.getMessage(), e);
        }
    }
}
