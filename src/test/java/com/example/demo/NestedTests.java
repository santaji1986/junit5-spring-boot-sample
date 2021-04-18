package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NestedTests {

    private String str;

    @Nested
    @DisplayName("For an empty string")
    class EmptyStringTests {

        @BeforeEach
        void setToEmpty() {
            str = "";
        }

        @Test
        @DisplayName(value = "length should be zero")
        void lengthIsZero() {
            assertEquals(0, str.length());
        }

        @Test
        @DisplayName(value = "uppercase is empty")
        void uppercaseIsEmpty() {
            assertEquals("", str.toUpperCase());
        }
    }

    @Nested
    @DisplayName("For a large strings")
    class LargeStringTests {

        @BeforeEach
        void setString() {
            str = "abdfjlwerjekabdfjlwerjekabdfjlwerjekabdfjlwerjek";
        }

        @Test
        @DisplayName(value = "length should not be zero")
        void lengthIsZero() {
            assertNotEquals(0, str.length());
        }

        @Test
        @DisplayName(value = "uppercase is not empty")
        void uppercaseIsEmpty() {
            assertNotEquals("", str.toUpperCase());
        }
    }
}
