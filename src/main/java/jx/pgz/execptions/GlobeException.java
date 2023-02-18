package jx.pgz.execptions;

import jx.pgz.utils.Result;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobeException {


    @ExceptionHandler(ResultException.class)
    public Result<String> bindException(ResultException exception) {
        String[] split = exception.getMessage().split("&");
        return  Result.build(null, Integer.valueOf(split[1]),split[0]);
    }


    @ExceptionHandler(RuntimeException.class)
    public Result<String> bindException(RuntimeException exception) {
        return Result.fail(exception.getMessage());
    }


}
