package com.freenow.exception;

import com.freenow.util.Consts;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = Consts.CAR_NOT_SELECTED_EXCEPTION)
public class CarNotSelectedByDriverException extends Exception{
    static final long serialVersionUID = -3387516993224229948L;


    public CarNotSelectedByDriverException(String message)
    {
        super(message);
    }
}
