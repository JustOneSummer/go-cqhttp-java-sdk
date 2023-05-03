package com.shinoaki.cqhttp.sdk.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;

/**
 * @author Xun
 * @date 2023/3/18 13:51 星期六
 */
public class JsonUtils {
    /**
     * JSON工具
     */
    private final ObjectMapper objectMapper;


    public JsonUtils() {
        this.objectMapper = initial(null).build();
    }

    /**
     * @param strategy 通过{@link  PropertyNamingStrategies}指定 可以为null
     */
    public JsonUtils(PropertyNamingStrategy strategy) {
        this.objectMapper = initial(strategy).build();
    }

    /**
     * 序列化Java Bean为JSON数据
     *
     * @param data Java Bean
     * @param <T>  类型
     * @return JSON数据
     */
    public <T> String toJson(T data) throws JsonProcessingException {
        return objectMapper.writeValueAsString(data);
    }

    /**
     * 解析JSON文件返回Java Bean
     *
     * @param json   json数据
     * @param tClass 类型
     * @param <T>    类型T
     * @return Java Bean
     */
    public <T> T parse(String json, Class<T> tClass) throws JsonProcessingException {
        return objectMapper.readValue(json, tClass);
    }

    /**
     * 解析JSON文件返回Java Bean
     *
     * @param json   json数据
     * @param tClass 类型
     * @param <T>    类型T
     * @return Java Bean
     */
    public <T> T parse(JsonNode json, Class<T> tClass) throws JsonProcessingException {
        return objectMapper.treeToValue(json, tClass);
    }

    /**
     * 解析JSON文件返回JsonNode
     *
     * @param json json数据
     * @return JsonNode
     */
    public JsonNode parse(String json) throws JsonProcessingException {
        return parse(json, JsonNode.class);
    }

    /**
     * 解析JSON文件返回Java Bean-应对Java复杂类型的Bean
     *
     * @param json json数据
     * @param type 复杂类型
     * @param <T>  类型T
     * @return Java Bean
     */
    public <T> T parse(String json, TypeReference<T> type) throws JsonProcessingException {
        return objectMapper.readValue(json, type);
    }


    /**
     * @param strategy 通过{@link  PropertyNamingStrategies}指定 可以为null
     */
    private static JsonMapper.Builder initial(PropertyNamingStrategy strategy) {
        //使用默认的策略 如有特殊情况请请额外解决 通过注释 JsonNaming
        return JsonMapper.builder().serializationInclusion(JsonInclude.Include.ALWAYS).propertyNamingStrategy(strategy)
                //JSON 序列化移除 transient 修饰的 Page 无关紧要的返回属性(Mybatis Plus)
                .configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true)
                //忽略java属性中不存在的字符
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
