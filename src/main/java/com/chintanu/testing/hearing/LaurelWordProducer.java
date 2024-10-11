package com.chintanu.testing.hearing;

import org.springframework.stereotype.Component;

@Component
public class LaurelWordProducer implements WordProducer{
    
    @Override
    public String sayTheWord() {
        return "Laurel";
    }
}
