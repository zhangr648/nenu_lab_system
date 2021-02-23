package cn.xiaoyh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private static final Integer SUCCESS_CODE = -1;
    private static final Integer FAILED_CODE = 1;

    private static final String SUCCESS_MESSAGE = "操作成功";
    private static final String FAILED_MESSAGE = "操作失败，请稍后再试";

    private Integer code;
    private String msg;
    private Object data;

    public static final Result OK = new Result(SUCCESS_CODE, SUCCESS_MESSAGE, null);
    public static final Result ERROR = new Result(FAILED_CODE, FAILED_MESSAGE, null);

    public static Result success(Object data) {
        return new Result(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static Result failed(Object data) {
        return new Result(SUCCESS_CODE, FAILED_MESSAGE, data);
    }
}
