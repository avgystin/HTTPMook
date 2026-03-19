package com.example.MookHTTP.configs;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "response.delay.static")
public class DelayStaticConfig {
    private long defaultDelay;
    private long get_1;
    private long get_2;
    private long get_3;
}
