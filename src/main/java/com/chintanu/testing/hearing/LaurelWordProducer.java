package com.chintanu.testing.hearing;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Primary
@Profile("laurel")
public class LaurelWordProducer implements WordProducer{
    
    @Override
    public String sayTheWord() {
        return "Laurel";
    }
}
