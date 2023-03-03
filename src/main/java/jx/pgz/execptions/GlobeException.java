package jx.pgz.execptions;

import jx.pgz.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
        return Result.fail().setMsg(exception.getMessage());
    }


    @ExceptionHandler(MyRuntimeException.class)
    public Result<Object> bindException(MyRuntimeException exception) {
        exception.printStackTrace();
        return Result.fail().setMsg(exception.getMessage());
    }


}
