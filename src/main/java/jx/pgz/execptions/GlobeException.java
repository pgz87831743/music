package jx.pgz.execptions;

import jx.pgz.enums.ResultCodeEnum;
import jx.pgz.utils.Result;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobeException {


    @ExceptionHandler(Exception.class)
    public Result<Object> bindException(Exception exception) {
        exception.printStackTrace();
        return Result.fail(exception.getMessage());
    }


    @ExceptionHandler(MyRuntimeException.class)
    public Result<Object> bindException(MyRuntimeException exception) {
        exception.printStackTrace();
        return Result.fail(exception.getMsg(), exception.getCode() == 0 ? ResultCodeEnum.FAIL.getCode() : exception.getCode()).setShowMsg(true).setSuccess(false);
    }

}
