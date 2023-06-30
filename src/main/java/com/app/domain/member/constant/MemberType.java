package com.app.domain.member.constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MemberType {

    KAKAO;

    //1 인자 -> 대문자 변경
    //2 위의 enum 타입이 있으면 true 리턴, 없으면 false
    public static MemberType from(String type) {
        return MemberType.valueOf(type.toUpperCase());
    }

    public static boolean isMemberType(String type) {
        MemberType[] values = MemberType.values();
        List<MemberType> collect = Arrays.stream(values).filter(mt -> mt == MemberType.from(type)).collect(Collectors.toList());
        return collect.size() != 0;
    }

}
