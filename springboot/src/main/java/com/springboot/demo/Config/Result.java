package com.springboot.demo.Config;

/**
 * @author LiZeyuan
 * @date 2022/1/26 12:37
 * 返回前台数据包装类
 */
public class Result<T> {
    private String code;
    private String msg;
    private T data;


    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Result success() {
        Result result = new Result<>();
        result.setCode("0");
        result.setMsg("success");
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>(data);
        result.setCode("0");
        result.setMsg("success");
        return result;
    }

    public static Result error(String code,String msg) {
        Result result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
