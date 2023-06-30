package com.app.api.member;

import com.app.api.member.dto.MemberInfoResponseDto;
import com.app.api.resolver.MemberInfo;
import com.app.api.resolver.MemberInfoDto;
import com.app.global.jwt.TokenManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberInfoController {

    private final MemberInfoService memberInfoService;
    private final TokenManager tokenManager;

    @GetMapping("/info")
    public ResponseEntity<MemberInfoResponseDto> getMemberInfo(@MemberInfo MemberInfoDto memberInfoDto) {

        Long memberId = memberInfoDto.getMemberId();
        return ResponseEntity.ok(memberInfoService.getMemberInfo(memberId));
    }

}
