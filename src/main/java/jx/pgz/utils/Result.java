package jx.pgz.utils;

import jx.pgz.enums.ResultCodeEnum;
import lombok.Data;

import java.io.Serializable;


@Data
public class Result<T> implements Serializable {

    //状态码
    private Integer code;

    //数据
    private T data;

    //操作信息
    private String msg;

    private Long expirationTime=UserContext.getInstance().getExpirationTime();


    public static <T> Result<T> ok(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static <T> Result<T> ok(T data,String msg) {
        return build(data, 200,msg);
    }
    public static <T> Result<T> ok(T data,int code) {
        return build(data, code,ResultCodeEnum.SUCCESS.getMessage());
    }

    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> fail(T data) {
        return build(data, ResultCodeEnum.FAIL);
    }


    public static <T> Result<T> fail() {
        return fail(null);
    }


    public static <T> Result<T> build(T data, ResultCodeEnum resultCodeEnum) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setMsg(resultCodeEnum.getMessage());
        result.setCode(resultCodeEnum.getCode());
        return result;
    }

    public static <T> Result<T> build(T data, Integer code, String message) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setMsg(message);
        result.setCode(code);
        return result;
    }


}
