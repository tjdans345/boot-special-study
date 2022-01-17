package com.example.bootspecialstudy.config;

import io.sentry.Sentry;
import io.sentry.SentryEvent;
import io.sentry.SentryLevel;
import io.sentry.protocol.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

public class SentrySupport {

//    public SentrySupport() {
//        System.out.println("================ Sentry Support init()");
//        // Sentry DSN 주소
//        Sentry.init(options -> {
//            options.setDsn("https://989a66639b44467aa94c61fde926cc67@o1117813.ingest.sentry.io/6151643");
//        });
//
//    }

    public static void userExceptionLog() {
        Message message = new Message();
        message.setMessage("유저 익셉션입니다.");

        SentryEvent sentryEvent = new SentryEvent();
        sentryEvent.setTag("test_id", UUID.randomUUID().toString());
        sentryEvent.setLevel(SentryLevel.WARNING);
        sentryEvent.setMessage(message);

        Sentry.captureEvent(sentryEvent);



    }




}
