package com.magic.bear.dict.exception;

import com.magic.bear.dict.constants.ResultCodeEnum;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class DictException extends RuntimeException{

    private Integer errorCode;

    private String errorMessage;



    public DictException(Integer errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public DictException(Integer errorCode, String errorMessage, Object data){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public DictException(ResultCodeEnum resultCodeEnum){
        this.errorCode = resultCodeEnum.getValue();
        this.errorMessage = resultCodeEnum.getInfo();
    }

    public DictException(ResultCodeEnum resultCodeEnum, HttpStatus httpStatus){
        this.errorCode = resultCodeEnum.getValue();
        this.errorMessage = resultCodeEnum.getInfo();
    }

    public DictException(Integer errorCode, String errorMessage, Object data, HttpStatus httpStatus){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public DictException(Integer errorCode, String errorMessage, HttpStatus httpStatus){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
