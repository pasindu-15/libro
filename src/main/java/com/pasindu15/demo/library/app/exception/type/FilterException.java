package com.pasindu15.demo.library.app.exception.type;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FilterException extends BaseException {
    public FilterException(String message) {
        super(message);
    }

    public FilterException(String message, String code) {
        super(message, code);
    }
}
