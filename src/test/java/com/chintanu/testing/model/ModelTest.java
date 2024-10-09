package com.chintanu.testing.model;

import org.junit.jupiter.api.*;

@Tag("model")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface ModelTest {

    @BeforeAll
    public default void setUp() {
        System.out.println("In Setup");
    }

    @BeforeEach
    default void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {

        System.out.println("Class : " + testInfo.getTestClass().get() + " Method : " + testInfo.getTestMethod().get() + " Display Name : " + testInfo.getDisplayName());
        System.out.println("Repetition info : " + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());
    }
}
