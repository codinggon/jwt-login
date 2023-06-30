package com.app.api.member.dto;

import com.app.domain.member.constant.Role;
import com.app.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class MemberInfoResponseDto {

    private Long memberId;
    private String email;
    private String memberName;
    private String profile;
    private Role role;

    public static MemberInfoResponseDto of(Member member) {
        return MemberInfoResponseDto.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .memberName(member.getMemberName())
                .profile(member.getProfile())
                .role(member.getRole())
                .build();
    }
}
