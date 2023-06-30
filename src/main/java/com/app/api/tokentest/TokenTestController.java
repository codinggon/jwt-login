package com.app.api.tokentest;

import com.app.domain.member.constant.Role;
import com.app.global.jwt.TokenManager;
import com.app.global.jwt.dto.JwtTokenDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token-test")
@RequiredArgsConstructor
public class TokenTestController {

    private final TokenManager tokenManager;

    @GetMapping("/create")
    public JwtTokenDto createJwtTokenDto(){
        return tokenManager.createJwtTokenDto(1L, Role.ADMIN);
    }

    @GetMapping("/valid")
    public String validateJwtToken(@RequestParam String token) {
        tokenManager.validateToken(token);
        Claims tokenClaims = tokenManager.getTokenClaims(token);
        Long memberId = Long.valueOf((Integer) tokenClaims.get("memberId"));
        String role = (String) tokenClaims.get("role");
        System.out.println("role = " + role);
        return "success";

    }


}
