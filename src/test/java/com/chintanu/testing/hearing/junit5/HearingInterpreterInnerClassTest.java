package com.chintanu.testing.hearing.junit5;

import com.chintanu.testing.hearing.HearingInterpreter;
import com.chintanu.testing.hearing.LaurelWordProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = {HearingInterpreterInnerClassTest.HearingInterpreterInnerClassConfig.class})
@ActiveProfiles("inner-class")
class HearingInterpreterInnerClassTest {

    @Configuration
    @Profile("inner-class")
    static class HearingInterpreterInnerClassConfig {

        @Bean
        public HearingInterpreter hearingInterpreter() {
            return new HearingInterpreter(new LaurelWordProducer());
        }
    }

    @Autowired
    HearingInterpreter interpreter;

    @Test
    public void interpretTheWord() {
        //given - nothing
        //when
        String word = interpreter.interpretTheWord();
        //then
        Assertions.assertEquals("Laurel", word);
    }
}