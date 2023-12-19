package com.jogo.tochat.advice;

import com.jogo.tochat.exceptions.AlreadyExistsException;
import com.jogo.tochat.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ApplicationExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<AppResp> catchInvalidArgumentException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new AppResp(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<AppResp> catchNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(new AppResp(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppResp> catchBusinessExceptionException(AlreadyExistsException e) {
        return new ResponseEntity<>(new AppResp(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
