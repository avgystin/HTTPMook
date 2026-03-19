package com.example.MookHTTP.services;

import com.example.MookHTTP.configs.DelayDynamicConfig;
import com.example.MookHTTP.repositories.DelayDynamicRepositories;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class DelayDynamicService {
    private static final Logger log = LoggerFactory.getLogger(DelayDynamicService.class);

    private final DelayDynamicConfig delayDynamicConfig;
    private final DelayDynamicRepositories delayDynamicRepositories;

    public static DelayDynamicConfig updateDelay(DelayDynamicConfig delayDynamicConfig, Map<String, Long> updateDelayData) {
        updateDelayData.forEach((key, value) -> {
            delayDynamicConfig.getDynamic().put(key, value);
        });
        return delayDynamicConfig;
    }


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

    public DelayDynamicConfig resetDelay() {
        delayDynamicConfig.getDynamic().clear();
        log.info("***********************OriginalDynamicDelay {}", delayDynamicRepositories.getOriginalDynamicDelay());
        return updateDelay(delayDynamicConfig, delayDynamicRepositories.getOriginalDynamicDelay());
    }
}




