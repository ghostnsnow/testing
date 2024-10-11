package com.chintanu.testing.hearing.config;

import com.chintanu.testing.hearing.LaurelWordProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LaurelConfig {

    @Bean
    public LaurelWordProducer laurelWordProducer() {
        return new LaurelWordProducer();
    }
}
