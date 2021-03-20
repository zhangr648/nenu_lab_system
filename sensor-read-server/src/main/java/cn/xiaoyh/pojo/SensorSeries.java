package cn.xiaoyh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SensorSeries {
    private String sensor;
    private List<Point> points;

    @Data
    @AllArgsConstructor
    public static class Point {
        private String time;
        private Object value;
    }
}
