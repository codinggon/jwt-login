package com.app.global.web;

import com.app.global.web.dto.KakaoTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class KakaoTokenController {

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.client.secret}")
    private String clientSecret;


    private final KakaoTokenClient kakaoTokenClient;

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    @GetMapping("/oauth/kakao/callback")
    public @ResponseBody KakaoTokenDto.Response loginCallback(String code) {

        KakaoTokenDto.Request request = KakaoTokenDto.Request.builder()
                .grant_type("authorization_code")
                .client_id(clientId)
                .client_secret(clientSecret)
                .redirect_uri("http://localhost:8080/oauth/kakao/callback")
                .code(code)
                .build();

        KakaoTokenDto.Response response = kakaoTokenClient.getAccessToken("application/x-www-form-urlencoded;charset=utf-8", request);
        System.out.println("response = " + response);

        return response;
    }


}
