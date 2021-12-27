package com.example.bootspecialstudy.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


// Exception 낚아채기
@RestController
//@RestControllerAdvice 내부에 @ControllerAdvice 를 상속받고 있음
@ControllerAdvice // @ControllerAdvice 는 모든 @Controller 즉, 전역에서 발생할 수 있는 예외를 잡아 처리해주는 annotation 이다.
public class MyExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public String illegalArgumentException(IllegalArgumentException illegalArgumentException) {
        return illegalArgumentException.getMessage();
    }

}
