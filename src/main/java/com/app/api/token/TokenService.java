package com.app.api.token;

import com.app.api.token.dto.AccessTokenResponseDto;
import com.app.domain.member.MemberService;
import com.app.domain.member.entity.Member;
import com.app.global.jwt.TokenManager;
import com.app.global.jwt.constant.GrantType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final MemberService memberService;
    private final TokenManager tokenManager;

    public AccessTokenResponseDto createAccessTokenByRefreshToken(String refreshToken) {

        //1. db refresh token 조회
        Member member = memberService.findMemberByRefreshToken(refreshToken);

        //2. access token 만료 시간 생성
        Date accessTokenExpireTime = tokenManager.createAccessTokenExpireTime();

        //3. access token 생성
        String accessToken = tokenManager.createAccessToken(member.getId(), member.getRole(), accessTokenExpireTime);

        //4. 반환
        return AccessTokenResponseDto.builder()
                .grantType(GrantType.BEARER.getType())
                        .accessToken(accessToken)
                        .accessTokenExpireTime(accessTokenExpireTime)
                        .build();
    }
}
