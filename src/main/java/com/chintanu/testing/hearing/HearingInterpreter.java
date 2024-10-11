package com.chintanu.testing.hearing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HearingInterpreter {

    WordProducer wordProducer;
    @Autowired
    public HearingInterpreter(WordProducer wordProducer) {
        this.wordProducer = wordProducer;
    }

    public String interpretTheWord() {

        System.out.println(wordProducer.sayTheWord());
        return wordProducer.sayTheWord();
    }
}
