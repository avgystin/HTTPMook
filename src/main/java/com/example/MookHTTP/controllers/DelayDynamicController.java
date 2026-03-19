package com.example.MookHTTP.controllers;

import com.example.MookHTTP.configs.DelayDynamicConfig;
import com.example.MookHTTP.services.DelayDynamicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class DelayDynamicController {

    private final DelayDynamicConfig delayDynamicConfig;
    private final DelayDynamicService delayDynamicService;


    @GetMapping(path = "/getDynamicDelay")
    public DelayDynamicConfig getDelay() {
        return delayDynamicConfig;
    }

    @PostMapping(path = "/postDynamicDelay")
    public DelayDynamicConfig postDelay(@RequestBody Map<String, Long> updateDelayData)
    {
        return DelayDynamicService.updateDelay(delayDynamicConfig, updateDelayData);
    }

    @GetMapping(path = "/resetDynamicDelay")
    public DelayDynamicConfig resetDelay() {
        return delayDynamicService.resetDelay();
    }
}
