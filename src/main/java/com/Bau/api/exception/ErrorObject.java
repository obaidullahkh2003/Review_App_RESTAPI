package com.Bau.api.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject {
    private int code;
    private String message;
    private Date timestamp;

}
