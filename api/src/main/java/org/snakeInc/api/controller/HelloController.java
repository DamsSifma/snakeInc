package org.snakeInc.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {
    @GetMapping
    public String GetHello(String name) {
        return "Hello" + " "  + name;
    }

    @PostMapping
    public String PostHello(BodyParam name) {
        return "Hello" + " "  + name.name();
    }

    public record BodyParam(String name) {

    }
}
