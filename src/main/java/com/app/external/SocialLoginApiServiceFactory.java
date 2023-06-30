package com.app.external;

import com.app.domain.member.constant.MemberType;
import com.app.external.service.SocialLoginApiService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SocialLoginApiServiceFactory {

    private static Map<String, SocialLoginApiService> socialLoginApiServiceMap;

    public SocialLoginApiServiceFactory(Map<String, SocialLoginApiService> socialLoginApiServiceMap) {
        this.socialLoginApiServiceMap = socialLoginApiServiceMap;
    }

    public static SocialLoginApiService getSocialLoginApiService(MemberType memberType) {
        String socialLoginApiServiceBeanName = "";

        if (MemberType.KAKAO.equals(memberType)) {
            socialLoginApiServiceBeanName = "kakaoLoginApiServiceImpl";
        }
        return socialLoginApiServiceMap.get(socialLoginApiServiceBeanName);
    }

}
