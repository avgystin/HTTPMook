package com.example.MookHTTP.repositories;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;


@Data
@Component
@ConfigurationProperties(prefix = "response.delay.static")
public class DelayStaticRepositories {
    private long defaultDelay;
    private long get_1;
    private long get_2;
    private long get_3;

    public Map<String, Object> getOriginalFromYaml() {
        return Map.of(
                "defaultDelay", defaultDelay,
                "get_1", get_1,
                "get_2", get_2,
                "get_3", get_3
        );
    }
}
