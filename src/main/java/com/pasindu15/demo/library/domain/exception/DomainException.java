package com.pasindu15.demo.library.domain.exception;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.pasindu15.demo.library.application.exception.type.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DomainException extends BaseException {
    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, String code) {
        super(message, code);
    }
}
