package com.chintanu.testing.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest implements ModelTest {

    @Test
    @Disabled
    public void testSout() {

        System.out.println("In SOUT Person");
    }

    @RepeatedTest(value = 5, name = "{displayName} :: repetition {currentRepetition} of {totalRepetitions}")
    @DisplayName("Person Repeated Test")
    public void testRepeated() {

        System.out.println("In testRepeated Person");
    }
}