package com.example.bootspecialstudy.config;


import com.example.bootspecialstudy.domain.dto.CommonDto;
import io.sentry.Sentry;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

// 메모리에 띄워야함
// 컨트롤러 진입전에 설정할 때는 @Configuration
@Component
@Aspect
public class BindingAdvice {

    private static final Logger log = LoggerFactory.getLogger(BindingAdvice.class);

    // 함수 : 앞 뒤 제어 @Around
    // 함수 : 앞 만 제어 @Before
    // 함수 : 뒤 만 제어 (응답만 관리) @After
    // @Around("execution(* com.example.bootspecialstudy.web..*Controller.메서드명도 가능)")
    // ProceedingJoinPoint 가 메서드를 들고온다.
    @Around("execution(* com.example.bootspecialstudy.web..*Controller.*(..))")
    public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String method = proceedingJoinPoint.getSignature().getName();

        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg: args) {
            if(arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;

                // 서비스 : 정상적인 화면 -> 사용자 요청
                if(bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();
                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                        // 로그에는 여러가지 레벨이 있다. error, warn, info, debug
                        log.warn(type+"."+method+"() -> 필드 : " +error.getField() + ", 메시지 : " + error.getDefaultMessage());
                        Sentry.captureException(new Exception(" anjsep "));
                        // 오류가 생겼을 때 DB연결 후 DB에 남기기.
                        // 파일로 남기기 -> 좋지 않은 방법이다.
                        // 센트리
                    }
                    return new ResponseEntity<>(new CommonDto<>(HttpStatus.BAD_REQUEST.value(), errorMap), HttpStatus.OK);
                }
            }
        }
        return proceedingJoinPoint.proceed(); // 함수의 스택을 실행해라
    }

    // 어던 함수가 언제 몇번 실행됐는지 횟수같은거 로그 남기기
//    @Before("execution(* com.example.bootspecialstudy.web..*Controller.*(..))")
//    public void logCheck(JoinPoint joinPoint) {
//
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        System.out.println("주소 : " + request.getRequestURI());
//
//        // 전 처리때는 request 값을 처리 못하나요 ?
//        // log 처리는 ? 파일로 어떻게 남길까 ?
//        System.out.println("로그를 남김");
//    }

    @After("execution(* com.example.bootspecialstudy.web..*Controller.*(..))")
    public void afterCheck(JoinPoint joinPoint) {

        // 메소드 앞으로 전처리 하고싶으면 AOP 사용
        System.out.println("후처리 로그를 남김");

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        System.out.println("주소 : " + request.getRequestURI());
    }

}
