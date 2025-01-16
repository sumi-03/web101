package com.example.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Controller가 json을 반환하도록
public class HelloController {

    @GetMapping("/hello") // HTTP의 GET 메소드를 요청받을수 있는 API를 생성
    public String hello() {
        return "Hello, Spring Boot!";
    }
}