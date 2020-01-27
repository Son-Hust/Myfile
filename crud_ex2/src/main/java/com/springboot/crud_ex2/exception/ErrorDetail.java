package com.springboot.crud_ex2.exception;


import lombok.Data;

import java.util.Date;

@Data
public class ErrorDetail {
    private Date timeStamp;
    private String message;
    private String detail;

    public ErrorDetail() {
    }

    public ErrorDetail(Date timeStamp, String message, String detail) {
        super();
        this.timeStamp = timeStamp;
        this.message = message;
        this.detail = detail;
    }
}
