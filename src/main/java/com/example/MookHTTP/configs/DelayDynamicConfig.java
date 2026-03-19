package com.example.MookHTTP.configs;


import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "response.delay")
public class DelayDynamicConfig {
    private static final Logger log = LoggerFactory.getLogger(DelayDynamicConfig.class);

    private Map<String, Long> dynamic;  // все кастомные эндпоинты

    public long getDynamicDelay(String endpoint) {
        log.info("***************************dynamic map: {}", dynamic);
        return dynamic.getOrDefault(endpoint, dynamic.get("defaultDelay"));
    }
}
