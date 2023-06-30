package com.app.api.logout;

import com.app.domain.member.MemberService;
import com.app.domain.member.entity.Member;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.AuthenticationException;
import com.app.global.jwt.TokenManager;
import com.app.global.jwt.constant.TokenType;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class LogoutService {

    private final MemberService memberService;
    private final TokenManager tokenManager;

    public void logout(String accessToken) {

        //1. 토큰 검증
        tokenManager.validateToken(accessToken);

        //2. 토큰 타입 확인
        Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
        String tokenType = tokenClaims.getSubject();//ACCESS

        if (!TokenType.isAccessToken(tokenType)) {
            throw new AuthenticationException(ErrorCode.NOT_ACCESS_TOKEN_TYPE);
        }

        //3. 토큰 만료 처리
        Long memberId = Long.valueOf((Integer)tokenClaims.get("memberId"));
        Member member = memberService.findMemberById(memberId);
        member.expireRefreshToken(LocalDateTime.now()); //refresh 토큰 만료
    }



}
