package com.chintanu.testing.hearing.config;

import com.chintanu.testing.hearing.HearingInterpreter;
import com.chintanu.testing.hearing.WordProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("base-test")
public class BaseConfig {

    @Bean
    public HearingInterpreter hearingInterpreter(WordProducer wordProducer) {
        return new HearingInterpreter(wordProducer);
    }

}
