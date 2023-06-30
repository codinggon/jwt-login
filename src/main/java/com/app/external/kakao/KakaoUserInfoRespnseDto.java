package com.app.external.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @Builder
@ToString
public class KakaoUserInfoRespnseDto {

    private Long id; //	Long	회원번호
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount; //	KakaoAccount	카카오계정 정보

    @Getter @Setter @Builder
    public static class KakaoAccount {

        private Profile profile; //	Profile	프로필 정보
        private String name; //	String	카카오계정 이름
        private String email; //	String	카카오계정 대표 이메일
        private String birthyear; //	String	출생 연도(YYYY 형식)
        private String phone_number; //	String

        @Getter @Setter @Builder
        public static  class Profile {
            private String nickname; //	String	닉네임
            @JsonProperty("profile_image_url")
            private String profileImageUrl; //	String	프로필 사진 URL
        }
    }
}
