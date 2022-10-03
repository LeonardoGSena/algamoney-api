package com.sena.leonardo.algamoneyapi.api.exceptionhandler;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {
    private MessageSource messageSource;

    public AlgamoneyExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String userMsg = messageSource.getMessage("InvalidMessage", null, LocaleContextHolder.getLocale());
        String devMsg = ex.getCause().toString();
        return handleExceptionInternal(ex, new Error(userMsg, devMsg), headers, HttpStatus.BAD_REQUEST, request);
    }

    public static class Error {
        private String userMsg;
        private String devMsg;

        public Error(String userMsg, String devMsg) {
            this.userMsg = userMsg;
            this.devMsg = devMsg;
        }

        public String getUserMsg() {
            return userMsg;
        }

        public String getDevMsg() {
            return devMsg;
        }
    }

}
