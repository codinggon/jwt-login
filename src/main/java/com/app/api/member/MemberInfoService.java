package com.app.api.member;

import com.app.api.member.dto.MemberInfoResponseDto;
import com.app.domain.member.MemberRepository;
import com.app.domain.member.entity.Member;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.lang.model.type.ErrorType;

@Service
@RequiredArgsConstructor
public class MemberInfoService {

    private final MemberRepository memberRepository;

    public MemberInfoResponseDto getMemberInfo(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new AuthenticationException(ErrorCode.NOT_FOUND_MEMBER_ID));
        return MemberInfoResponseDto.of(member);
    }
}
