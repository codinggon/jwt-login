package com.app.global.web;

import com.app.api.health.dto.HealthCheckResponseDto;
import com.app.global.web.dto.KakaoTokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://kauth.kakao.com", name = "kakaoClient")
public interface KakaoTokenClient {

    @PostMapping(value = "/oauth/token", consumes = "application/json")
    KakaoTokenDto.Response getAccessToken(@RequestHeader("Content-type") String contentType,
                                       @SpringQueryMap KakaoTokenDto.Request request);

}
