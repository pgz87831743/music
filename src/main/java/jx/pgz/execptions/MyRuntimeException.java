package jx.pgz.execptions;

import lombok.Data;

@Data
public class MyRuntimeException extends RuntimeException{
    private String msg;
    private int code;

    public MyRuntimeException(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public MyRuntimeException(String msg) {
        this.msg = msg;
    }
}
