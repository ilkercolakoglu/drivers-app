package com.freenow.exception;

import com.freenow.util.Consts;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = Consts.CAR_ALREADY_IN_USE_EXCEPTION)
public class CarAlreadyInUseException extends Exception {
    static final long serialVersionUID = -3387516993224229948L;


    public CarAlreadyInUseException(String message)
    {
        super(message);
    }
}
