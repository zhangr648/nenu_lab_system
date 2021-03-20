package cn.xiaoyh.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class Result {

    private static final String SUCCESS_MESSAGE = "操作成功";
    private static final String FAILED_MESSAGE = "操作失败，请稍后再试";

    private Boolean success;
    private Integer code;
    private String msg;
    private Map<String, Object> data = new HashMap<>();

    private Result() {
    }

    public static Result ok() {
        return new Result().setSuccess(true).setCode(ResultCode.SUCCESS).setMsg(SUCCESS_MESSAGE);
    }

    public static Result error() {
        return new Result().setSuccess(false).setCode(ResultCode.ERROR).setMsg(SUCCESS_MESSAGE);
    }

    public Result message(String message){
        this.setMsg(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
