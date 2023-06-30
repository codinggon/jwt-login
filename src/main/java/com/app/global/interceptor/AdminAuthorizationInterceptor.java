package com.app.global.interceptor;

import com.app.domain.member.constant.Role;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.AuthenticationException;
import com.app.global.jwt.TokenManager;
import com.app.global.jwt.constant.TokenType;
import com.app.global.util.AuthorizationHeaderUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AdminAuthorizationInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 인증관련
        //1. Authorization Header 검증
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.split(" ")[1];
        Claims tokenClaims = tokenManager.getTokenClaims(token);
        String role = (String) tokenClaims.get("role");
        if (!Role.ADMIN.equals(Role.valueOf(role))) {
            throw new AuthenticationException(ErrorCode.FORBIDDEN_ADMIN);
        }


        return true;
    }
}
