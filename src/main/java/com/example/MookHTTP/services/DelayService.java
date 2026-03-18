package com.example.MookHTTP.services;


import com.example.MookHTTP.configs.DelayConfig;
import com.example.MookHTTP.repositories.DelayRepositories;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.TimeUnit;



@RequiredArgsConstructor
@Service
public class DelayService {

    private static final Logger log = LoggerFactory.getLogger(DelayService.class);

    private final DelayConfig delayConfig;
    private final DelayRepositories delayRepositories;



    public static DelayConfig updateDelay(DelayConfig delayConfig, Map<String, Object> updateDelayData) {
        updateDelayData.forEach((key, value) -> {
            try {
                Field field = delayConfig.getClass().getDeclaredField(key);
                field.setAccessible(true);
                field.set(delayConfig, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.warn(String.valueOf(e));
            }
        });
        return delayConfig;
    }

    public DelayConfig resetDelay() {
        updateDelay(delayConfig, delayRepositories.getOriginalFromYaml());
        return delayConfig;
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
            case "get_1" -> delayConfig.getGet_1();
            case "get_2" -> delayConfig.getGet_2();
            case "get_3" -> delayConfig.getGet_3();
            default -> delayConfig.getDefaultDelay();
        };
    }

}
