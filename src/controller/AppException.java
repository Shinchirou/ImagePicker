package controller;

import java.time.LocalDateTime;

public class AppException extends RuntimeException {
    private String msg;
    private LocalDateTime date;

    public AppException(String msg, LocalDateTime date) {
        this.msg = msg;
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }
}
