package com.hero.api;

public class GlobalException extends RuntimeException {

    public static final int USER_PERMISSION = 510;
    public static final int TIMESTAMP_ERROR = 511;
    public static final int SIGN_ERROR = 512;
    public static final int NO_PERMISSION_CALL = 513;
    public static final int ACCESSKEY_ERROR = 514;

    private int code = -1;
    private String msg;

    public GlobalException(String message) {
        super(message);
        this.msg = message;
    }

    public GlobalException() {
        super();
    }


    public GlobalException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
