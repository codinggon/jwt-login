package com.app.api.login.validator;

import com.app.domain.member.constant.MemberType;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.AuthenticationException;
import com.app.global.jwt.constant.GrantType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class OauthValidator {


    public void validateMemberType(String memberType) {
        if (!MemberType.isMemberType(memberType)) {
            throw new AuthenticationException(ErrorCode.NOT_EXIST_MEMBER_TYPE);
        }
    }


}
