package com.example.MookHTTP.services;

import com.example.MookHTTP.configs.DelayDynamicConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class DelayDynamicService {

    private final DelayDynamicConfig delayDynamicConfig;


    public void applyDynamicDelay(String endpoint, long startTime) {
        long endpointDelay = delayDynamicConfig.getDynamicDelay(endpoint);
        long realDelay = System.currentTimeMillis() - startTime;
        if (endpointDelay > realDelay) {
            try {
                TimeUnit.MILLISECONDS.sleep(endpointDelay - realDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}




