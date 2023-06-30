package com.app.api.login;

import com.app.api.login.dto.OauthLoginDto;
import com.app.api.login.validator.OauthValidator;
import com.app.domain.member.constant.MemberType;
import com.app.global.util.AuthorizationHeaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class OauthLoginController {

    private final OauthValidator oauthValidator;
    private final OauthLoginService oauthLoginService;

    //local login 부분
    @GetMapping("/login")
    public ResponseEntity<OauthLoginDto.Response> oauthLogin(@RequestBody OauthLoginDto.Request request,
                                                             HttpServletRequest httpServletRequest) {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorizationHeader); //
        oauthValidator.validateMemberType(request.getMemberType());

        OauthLoginDto.Response response = oauthLoginService.oauthLogin(authorizationHeader.split(" ")[1], MemberType.from(request.getMemberType()));
//        System.out.println("response = " + response);

        return ResponseEntity.ok(response);
    }

}
