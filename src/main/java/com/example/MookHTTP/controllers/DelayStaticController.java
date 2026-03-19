package com.example.MookHTTP.controllers;

import com.example.MookHTTP.configs.DelayStaticConfig;
import com.example.MookHTTP.services.DelayStaticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class DelayStaticController {

    private final DelayStaticConfig delayStaticConfig;
    private final DelayStaticService delayStaticService;


    @GetMapping(path = "/getStaticDelay")
    public DelayStaticConfig getDelay() {
        return delayStaticConfig;
    }

    @PostMapping(path = "/postStaticDelay")
    public DelayStaticConfig postDelay(@RequestBody Map<String, Object> updateDelayData)
    {
        return delayStaticService.updateDelay(delayStaticConfig, updateDelayData);
    }

    @GetMapping(path = "/resetStaticDelay")
    private DelayStaticConfig resetDelay() {
        return delayStaticService.resetDelay();
    }
}
