package com.example.MookHTTP.controllers;

import com.example.MookHTTP.configs.DelayConfig;
import com.example.MookHTTP.services.DelayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class DelayController {

    private final DelayConfig delayConfig;
    private final DelayService delayService;


    @GetMapping(path = "/getDelay")
    public DelayConfig getDelay() {
        return delayConfig;
    }

    @PostMapping(path = "/postDelay")
    public DelayConfig postDelay(@RequestBody Map<String, Object> updateDelayData)
    {
        return DelayService.updateDelay(delayConfig, updateDelayData);
    }

    @GetMapping(path = "/resetDelay")
    public DelayConfig resetDelay() {
        return delayService.resetDelay();
    }
}
