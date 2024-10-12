package com.chintanu.testing.hearing.junit5;

import com.chintanu.testing.hearing.HearingInterpreter;
import com.chintanu.testing.hearing.config.BaseConfig;
import com.chintanu.testing.hearing.config.LaurelConfig;
import com.chintanu.testing.hearing.config.YannyConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {BaseConfig.class, LaurelConfig.class})
@SpringJUnitConfig(classes = {BaseConfig.class, YannyConfig.class})
@ActiveProfiles("base-test")
class HearingInterpreterTest {

    @Autowired
    HearingInterpreter interpreter;

    @Test
    public void interpretTheWord() {
        //given - nothing
        //when
        String word = interpreter.interpretTheWord();
        //then
        Assertions.assertEquals("Yanny", word);
    }
}