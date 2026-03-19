package com.example.MookHTTP.services;


import com.example.MookHTTP.configs.DelayStaticConfig;
import com.example.MookHTTP.repositories.DelayStaticRepositories;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.TimeUnit;



@RequiredArgsConstructor
@Service
public class DelayStaticService {

    private static final Logger log = LoggerFactory.getLogger(DelayStaticService.class);

    private final DelayStaticConfig delayStaticConfig;
    private final DelayStaticRepositories delayStaticRepositories;



    public DelayStaticConfig updateDelay(DelayStaticConfig delayStaticConfig, Map<String, Object> updateDelayData) {
        updateDelayData.forEach((key, value) -> {
            try {
                Field field = delayStaticConfig.getClass().getDeclaredField(key);
                field.setAccessible(true);
                field.set(delayStaticConfig, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.warn(String.valueOf(e));
            }
        });
        return delayStaticConfig;
    }

    public DelayStaticConfig resetDelay() {
        updateDelay(delayStaticConfig, delayStaticRepositories.getOriginalFromYaml());
        return delayStaticConfig;
    }


    public void applyDelay(String endpoint, long startTime) {
        long endpointDelay = getDalayEndpoint(endpoint);
        long realDelay = System.currentTimeMillis() - startTime;
        if (endpointDelay > realDelay) {
            try {
                TimeUnit.MILLISECONDS.sleep(endpointDelay - realDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private long getDalayEndpoint(String endpoint) {
        return switch (endpoint) {
            case "get_1" -> delayStaticConfig.getGet_1();
            case "get_2" -> delayStaticConfig.getGet_2();
            case "get_3" -> delayStaticConfig.getGet_3();
            default -> delayStaticConfig.getDefaultDelay();
        };
    }

}
