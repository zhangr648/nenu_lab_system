package cn.xiaoyh.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> String serialize(T obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T deserialize(String jsonText, Class<T> beanClass) {
        try {
            return MAPPER.readValue(jsonText, beanClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
