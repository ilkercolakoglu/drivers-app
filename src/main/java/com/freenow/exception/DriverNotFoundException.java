package com.freenow.exception;

import com.freenow.util.Consts;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = Consts.COULD_NOT_FIND_ANY_DRIVER)
public class DriverNotFoundException extends Exception
{
    static final long serialVersionUID = -3387516993334229948L;


    public DriverNotFoundException(String message)
    {
        super(message);
    }

}
