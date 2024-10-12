package com.chintanu.testing.hearing.config;

import com.chintanu.testing.hearing.YannyWordProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("base-test")
public class YannyConfig {

    @Bean
    public YannyWordProducer yannyWordProducer() {
        return new YannyWordProducer();
    }
}
