package cn.xiaoyh.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class SensorWriteReq {
    private String lab;
    private Long time;
    private Map<String, Object> sensors;
}
