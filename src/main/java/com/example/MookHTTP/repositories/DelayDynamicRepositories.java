package com.example.MookHTTP.repositories;

import com.example.MookHTTP.configs.DelayDynamicConfig;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;


@Data
@Component
@ConfigurationProperties(prefix = "response.delay.static")
public class DelayDynamicRepositories {
    private static final Logger log = LoggerFactory.getLogger(DelayDynamicConfig.class);

    private final DelayDynamicConfig delayDynamicConfig;
    private Map<String, Long> originalDynamicDelay;

    @PostConstruct
    public void init() {
        this.originalDynamicDelay = new HashMap<>(delayDynamicConfig.getDynamic());
    }
}
