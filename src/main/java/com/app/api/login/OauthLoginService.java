package com.app.api.login;

import com.app.api.login.dto.OauthLoginDto;
import com.app.domain.member.MemberService;
import com.app.domain.member.constant.MemberType;
import com.app.domain.member.constant.Role;
import com.app.domain.member.entity.Member;
import com.app.external.SocialLoginApiServiceFactory;
import com.app.external.modal.OAuthAttributes;
import com.app.external.service.SocialLoginApiService;
import com.app.global.jwt.TokenManager;
import com.app.global.jwt.dto.JwtTokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OauthLoginService {

    private final MemberService memberService;
    private final TokenManager tokenManager;

    public OauthLoginDto.Response oauthLogin(String accessToken, MemberType memberType) {
        //kakao 회원정보 가져와서 -> OAuthAttributes로 반환
        SocialLoginApiService socialLoginApiService = SocialLoginApiServiceFactory.getSocialLoginApiService(memberType);
        //kakao에서 user정보를 가져오기 때문에 access token값이 다를 경우 에러가 발생한다 (access 토큰의 유효성을 따로 검증할 필요가 없다.)
        OAuthAttributes userInfo = socialLoginApiService.getUserInfo(accessToken);
//        log.info("userinfo : {}", userInfo);

        Optional<Member> optionalMember = memberService.findMemberEmail(userInfo.getEmail());
        if (optionalMember.isEmpty()) { // 신규 회원

            Member oauthMember = userInfo.toMemberEntity(memberType, Role.USER);
            Member savedMember = memberService.registerMember(oauthMember);

            //토큰 생성 (kakao 로그인은 성공했고 -> local 로그인을 위해서 토큰을 재발급한다)
            JwtTokenDto jwtTokenDto = tokenManager.createJwtTokenDto(savedMember.getId(), savedMember.getRole());
            savedMember.updateRefreshToken(jwtTokenDto);

            return OauthLoginDto.Response.of(jwtTokenDto);

        } else{ //기존 회원

//            Member oauthMember = userInfo.toMemberEntity(memberType, Role.ADMIN);
            Member oauthMember = optionalMember.get();

            //토큰 생성 (kakao 로그인은 성공했고 -> local 로그인을 위해서 토큰을 재발급한다)
            JwtTokenDto jwtTokenDto = tokenManager.createJwtTokenDto(oauthMember.getId(), oauthMember.getRole());
            oauthMember.updateRefreshToken(jwtTokenDto);

            return OauthLoginDto.Response.of(jwtTokenDto);
        }
    }
}
