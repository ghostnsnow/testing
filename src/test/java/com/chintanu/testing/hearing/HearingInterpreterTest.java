package com.chintanu.testing.hearing;

import com.chintanu.testing.hearing.config.BaseConfig;
import com.chintanu.testing.hearing.config.LaurelConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BaseConfig.class, LaurelConfig.class})
class HearingInterpreterTest {

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