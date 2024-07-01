package com.codewithson.Elearning.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/test")
@RequiredArgsConstructor
public class Test {
    @PostMapping("")
    public String printHello() {
        return "hello world";
    }
}
