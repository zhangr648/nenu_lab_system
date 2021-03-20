package cn.xiaoyh.utils;

public class SensorUtils {
    public static final String RP_ONE_MONTH = "one_month";
    public static final String RP_ONE_YEAR = "one_year";

    public static final String KEY_TIME = "time";
    public static final String TIME_UNIT_MINUTE = "m";
    public static final String TIME_UNIT_HOUR = "h";

    public static final int MAX_HOUR_NUMS = 24;
    public static final int MAX_MINUTE_NUMS = 60;

    public static String getMeasurementWithRP(String rp, String measurement) {
        return String.format("\"%s\".\"%s\"", rp, measurement);
    }
}
