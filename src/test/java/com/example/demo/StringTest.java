package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class StringTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("StringTest.beforeAll()");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("StringTest.afterAll()");
    }

    @BeforeEach
    public void beforeEach(TestInfo info) {
        System.out.println("Init StringTest.beforeEach() : " + info.getDisplayName());
    }

    @AfterEach
    public void afterEach(TestInfo info) {
        System.out.println("Cleanup StringTest.afterEach() : " + info.getDisplayName());
    }

    @SuppressWarnings("null")
    @Test
    @DisplayName("When length is null, throw an NullPointerException")
    void length_exception() {
        final String str = null;
        assertThrows(NullPointerException.class, () -> {
            str.length();
        });
    }

    @ParameterizedTest
    @ValueSource(strings = { "ABCD", "AB", "ABC", "123" })
    void length_greater_than_zero_using_parameterized_test(String str) {
        assertTrue(str.length() > 0);
    }

    @ParameterizedTest(name = "{0} length is {1}")
    @CsvSource(value = { "abcd, 4", "abc, 3" })
    void length(String word, int expectedLength) {
        assertEquals(word.length(), expectedLength);
    }

    @Test
    void performanceTest() {
        assertTimeout(Duration.ofMinutes(5), () -> {
            for (int i = 0; i < 1_0; i++) {
                final int j = i;
                System.out.println(j);
            }
        });
    }

    @DisplayName("Add operation test")
    @RepeatedTest(value = 5, name = "{displayName} - repetition {currentRepetition} of {totalRepetitions}")
    void addNumber(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println("Running addNumber test -> " + repetitionInfo.getCurrentRepetition());
        assertEquals(2, Math.addExact(1, 1), "1 + 1 should equal 2");
    }

    @Test
    @Disabled
    void disabledTest() {
        System.out.println("This test will not run because its disabled.");
    }
}
