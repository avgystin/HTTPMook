package com.example.MookHTTP.controllers;


import com.example.MookHTTP.services.DelayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RestApiController {

    private final DelayService delayService;

    @GetMapping(path = "/get_1")
    public String get_1() {
        delayService.aplayDelay("get_1");
        return ("answer_get_1");
    }
    @GetMapping(path = "/get_2")
    public String get_2() {
        delayService.aplayDelay("get_2");
        return ("answer_get_2");
    }
    @GetMapping(path = "/get_3")
    public String get_3() {
        delayService.aplayDelay("get_3");
        return ("answer_get_3");
    }


}
