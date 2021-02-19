package cn.xiaoyh.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SensorEntity {
    private String college;
    private String lab;
    private String sensor;
    private String value;
    private String unit;
}
