package cn.xiaoyh.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Measurement(name = "test", database = "test")
public class SensorEntity {

    @Column(name = "college", tag = true)
    private String college;

    @Column(name = "lab", tag = true)
    private String lab;

    @Column(name = "sensor", tag = true)
    private String sensor;

    @Column(name = "value")
    private String value;

    @Column(name = "unit")
    private String unit;
}
