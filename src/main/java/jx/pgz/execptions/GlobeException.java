package jx.pgz.execptions;

import jx.pgz.enums.ResultCodeEnum;
import jx.pgz.utils.Result;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobeException {



    @ExceptionHandler(Throwable.class)
    public Result<Object> bindException(Throwable exception) {
        exception.printStackTrace();
        return Result.fail().setMsg(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Object> bindException(Exception exception) {
        exception.printStackTrace();
        return Result.fail().setMsg(exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<Object> bindException(RuntimeException exception) {
        exception.printStackTrace();
        return Result.fail().setMsg(exception.getMessage()).setShowMsg(true);
    }


    @ExceptionHandler(MyRuntimeException.class)
    public Result<Object> bindException(MyRuntimeException exception) {
        exception.printStackTrace();
        return Result.fail().setMsg(exception.getMsg()).setShowMsg(true);
    }


    @ExceptionHandler(UnauthorizedException.class)
    public Result<Object> bindException(UnauthorizedException exception) {
        exception.printStackTrace();
        return Result.fail(null).setMsg("暂无权限").setCode(ResultCodeEnum.TOKEN_ERROR.getCode());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<Object> bindException(HttpRequestMethodNotSupportedException exception) {
        exception.printStackTrace();
        return Result.fail(null).setMsg("登录过期").setCode(ResultCodeEnum.TOKEN_ERROR.getCode());
    }




}
