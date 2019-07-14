package com.manolo.grpcspringbooteurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private HelloServiceConsumer helloServiceConsumer;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        String greeting = helloServiceConsumer.sayHello("Manu", "Mura");
        return ResponseEntity.ok(greeting);
    }
}
