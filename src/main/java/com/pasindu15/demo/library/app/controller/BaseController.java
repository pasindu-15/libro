package com.pasindu15.demo.library.app.controller;

import com.pasindu15.demo.library.app.config.EnvConfig;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.logging.Logger;

public class BaseController {
    private static final Logger logger = Logger.getLogger(BaseController.class.getName());

    @Autowired
    private EnvConfig envConfig;

    public void setLogIdentifier(HttpServletRequest request) {

        String logIdentifier = request.getHeader(envConfig.getLogIdentifierKey());
        if (logIdentifier != null) {
            MDC.put(envConfig.getLogIdentifierKey(), logIdentifier);
        } else {
            MDC.put(envConfig.getLogIdentifierKey(), UUID.randomUUID().toString());
        }
    }
}
