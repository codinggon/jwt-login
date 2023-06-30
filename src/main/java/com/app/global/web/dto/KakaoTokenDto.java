package com.app.global.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class KakaoTokenDto {

    @Getter
    @Builder
    @ToString
    public static class Request{

        private String grant_type; //	String	authorization_code로 고정	O
        private String client_id; //	String	앱 REST API 키
        private String client_secret; //	String	앱 REST API 키
        private String redirect_uri; //	String	인가 코드가 리다이렉트된 URI	O
        private String code; //	String	인가 코드 받기 요청으로 얻은 인가 코드	O

    }

    @Getter
    @Builder
    @ToString
    public static class Response{

        private String token_type; //	String	토큰 타입, bearer로 고정	O
        private String access_token; //	String	사용자 액세스 토큰 값	O
        private Integer expires_in; //	Integer	액세스 토큰과 ID 토큰의 만료 시간(초)
        private String refresh_token; //	String	사용자 리프레시 토큰 값	O
        private Integer refresh_token_expires_in; //	Integer	리프레시 토큰 만료 시간(초)	O

    }






}
