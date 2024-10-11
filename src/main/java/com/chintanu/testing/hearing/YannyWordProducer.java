package com.chintanu.testing.hearing;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class YannyWordProducer implements WordProducer{

    @Override
    public String sayTheWord() {
        return "Yanny";
    }
}
