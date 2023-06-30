package com.app.external.kakao;

import com.app.domain.member.constant.MemberType;
import com.app.external.modal.OAuthAttributes;
import com.app.external.service.SocialLoginApiService;
import com.app.global.jwt.constant.GrantType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoLoginApiServiceImpl implements SocialLoginApiService {

    private final KakaoUserInfoClient kakaoUserInfoClient;

    private final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";


    @Override
    public OAuthAttributes getUserInfo(String accessToken) {

        KakaoUserInfoRespnseDto kakaoUserInfo = kakaoUserInfoClient.getKakaoUserInfo(CONTENT_TYPE, GrantType.BEARER.getType() + " " + accessToken);
        System.out.println("kakaoUserInfo = " + kakaoUserInfo);
        KakaoUserInfoRespnseDto.KakaoAccount kakaoAccount = kakaoUserInfo.getKakaoAccount();
        String email = kakaoAccount.getEmail();

        return OAuthAttributes.builder()
                .email(!StringUtils.hasText(email) ? String.valueOf(kakaoUserInfo.getId()) : email)
                .name(kakaoAccount.getProfile().getNickname())
                .profile(kakaoAccount.getProfile().getProfileImageUrl())
                .memberType(MemberType.KAKAO)
                .build();
    }
}
