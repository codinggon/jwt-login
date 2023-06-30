package com.app.api.exceptiontest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class ExceptionTestDto {

    @NotBlank
    private String value1;

    @Max(value = 10 , message = "입력값이 10보다 큽니다")
    private String value2;


}
