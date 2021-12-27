package com.example.bootspecialstudy.config;

// Exception을 낚아채기

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
//@RestControllerAdvice 내부에 @ControllerAdvice 를 상속받고 있음
@ControllerAdvice // @ControllerAdvice는 모든 @Controller 즉, 전역에서 발생할 수 있는 예외를 잡아 처리해주는 annotation이다.
public class MyExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public String illegalArgumentException(IllegalArgumentException illegalArgumentException) {
        return illegalArgumentException.getMessage();
    }

}
