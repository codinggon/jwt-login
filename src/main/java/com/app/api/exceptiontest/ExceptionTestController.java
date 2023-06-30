package com.app.api.exceptiontest;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exception")
public class ExceptionTestController {


    @GetMapping("/bind-exception-test")
    public String test(@Valid ExceptionTestDto exceptionTestDto) {
        return "ok";
    }




}
