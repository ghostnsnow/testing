package com.chintanu.testing.hearing.junit4;

import com.chintanu.testing.hearing.HearingInterpreter;
import com.chintanu.testing.hearing.config.BaseConfig;
import com.chintanu.testing.hearing.config.YannyConfig;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BaseConfig.class, YannyConfig.class})
public class HearingInterpreterTest {

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