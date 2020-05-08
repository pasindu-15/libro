package com.pasindu15.demo.library.external.exception;

import com.pasindu15.demo.library.app.exception.type.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class WebClientException extends BaseException {
    public WebClientException(String message) {
        super(message);
    }

    public WebClientException(String message, String code) {
        super(message, code);
    }
}
