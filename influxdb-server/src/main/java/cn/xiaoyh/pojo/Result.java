package cn.xiaoyh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private int code;
    private String msg;

    public static final Result OK = new Result(0, "操作成功");
    public static final Result ERROR = new Result(1, "操作异常，请稍后再试");
}
