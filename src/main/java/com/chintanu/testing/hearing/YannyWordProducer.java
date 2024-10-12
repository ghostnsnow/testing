package com.chintanu.testing.hearing;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("yanny")
//@Primary
public class YannyWordProducer implements WordProducer{

    @Override
    public String sayTheWord() {
        return "Yanny";
    }
}
