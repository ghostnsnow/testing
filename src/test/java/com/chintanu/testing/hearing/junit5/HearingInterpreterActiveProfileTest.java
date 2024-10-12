package com.chintanu.testing.hearing.junit5;

import com.chintanu.testing.hearing.HearingInterpreter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = {HearingInterpreterActiveProfileTest.HearingInterpreterActiveProfileConfig.class})
@ActiveProfiles("laurel")
class HearingInterpreterActiveProfileTest {

    @Autowired
    HearingInterpreter interpreter;

    @Configuration
    @ComponentScan(basePackages = {"com.chintanu.testing.hearing.config", "com.chintanu.testing.hearing"})
    //@Profile("active-profile")
    static class HearingInterpreterActiveProfileConfig {

    }

    @Test
    public void interpretTheWord() {
        //given - nothing
        //when
        String word = interpreter.interpretTheWord();
        //then
        Assertions.assertEquals("Laurel", word);
    }
}