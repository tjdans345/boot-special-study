package com.example.bootspecialstudy.config;


import com.example.bootspecialstudy.domain.dto.CommonDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

// 메모리에 띄워야함

// 컨트롤러 진입전에 설정할 때는 @Configuration
@Component
@Aspect
public class bindingAdvice {

    // 함수 : 앞 뒤 제어 @Around
    // 함수 : 앞 만 제어 @Before
    // 함수 : 뒤 만 제어 (응답만 관리) @After
    // @Around("execution(* com.example.bootspecialstudy.web..*Controller.메서드명도 가능)")
    // ProceedingJoinPoint 가 메서드를 들고온다.
    @Around("execution(* com.example.bootspecialstudy.web..*Controller.*(..))")
    public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
//        String method = proceedingJoinPoint.getSignature().getName();
//        System.out.println("type : " + type);
//        System.out.println("method : " + method);

        Object[] args = proceedingJoinPoint.getArgs();

        for (Object arg: args) {
            if(arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;

                if(bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();
                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }
                    return new CommonDto<>(HttpStatus.BAD_REQUEST.value(), errorMap.get("defaultMessage"));
                }
            }
        }
        return proceedingJoinPoint.proceed(); // 함수의 스택을 실행해라
    }

    @Before("execution(* com.example.bootspecialstudy.web..*Controller.*(..))")
    public void logCheck() {
        System.out.println("로그를 남김");
    }


}
