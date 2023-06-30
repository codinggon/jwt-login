package com.app.global;

import feign.Logger;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableFeignClients(basePackages = "com.app")
@Import(FeignClientsConfiguration.class)
public class FeignConfiguration {

    //    NONE: 로깅하지 않음(기본값)
//    BASIC: 요청 메소드와 URI와 응답 상태와 실행시간만 로깅함
//    HEADERS: 요청과 응답 헤더와 함께 기ㄹ본 정보들을 남김
//    FULL: 요청과 응답에 대한 헤더와 바디, 메타 데이터를 남김

    //logging level 설정 -> FULL
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    //error Decoder 주입
    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignClientExceptionErrorDecoder();
    }

    //retryer 생성
    @Bean
    public Retryer retryer() {
        //실행주기, ,maxAttampt : 재시도 횟수
        return new Retryer.Default(1000, 2000, 3);
    }

}
