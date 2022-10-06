package com.sena.leonardo.algamoneyapi.api.exceptionhandler;

import com.sena.leonardo.algamoneyapi.domain.exceptions.EntityNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String userMsg = messageSource.getMessage("InvalidMessage", null, LocaleContextHolder.getLocale());
        String devMsg = ex.getCause().toString();
        return handleExceptionInternal(ex, new Error(userMsg, devMsg), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Issue.Description> descriptions = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String field = ((FieldError) error).getField();
            String msg = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            descriptions.add(new Issue.Description(field, msg));
        }

        Issue issue = new Issue();
        issue.setStatus(status.value());
        issue.setDateHour(OffsetDateTime.now());
        issue.setTitle("There is a least one invalid data. Please correct the current failure and try again.");
        issue.setDescriptions(descriptions);
        return handleExceptionInternal(ex, issue, headers, status, request);
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Void> handleEmptyResultDataAccessException(){
        return ResponseEntity.notFound().build();
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
