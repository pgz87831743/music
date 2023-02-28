package jx.pgz.execptions;

import jx.pgz.enums.ResultCodeEnum;
import jx.pgz.utils.Result;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobeException {



    @ExceptionHandler(Throwable.class)
    public Result<String> bindException(Throwable exception) {
        exception.printStackTrace();
        return Result.fail(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> bindException(Exception exception) {
        exception.printStackTrace();
        return Result.fail(exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<Object> bindException(RuntimeException exception) {
        exception.printStackTrace();
        return Result.fail();
    }


    @ExceptionHandler(MyRuntimeException.class)
    public Result<Object> bindException(MyRuntimeException exception) {
        exception.printStackTrace();
        return Result.fail(exception.getMsg(), exception.getCode() == 0 ? ResultCodeEnum.FAIL.getCode() : exception.getCode());
    }


    @ExceptionHandler(UnauthorizedException.class)
    public Result<Object> bindException(UnauthorizedException exception) {
        exception.printStackTrace();
        return Result.fail(null).setMsg("暂无权限").setCode(ResultCodeEnum.TOKEN_ERROR.getCode());
    }




}
