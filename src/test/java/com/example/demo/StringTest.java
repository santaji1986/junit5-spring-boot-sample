package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class StringTest {
	private String str;

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

	@Test
	void testLength() {
		int actualLength = "ABCD".length();
		int expectedLength = 4;
		assertEquals(expectedLength, actualLength);
	}

	@Test
	void toUpperCase_basic() {
		String str = "abcd";
		String result = str.toUpperCase();
		assertNotNull(result);
		assertEquals("ABCD", result);
	}

	@Test
	@DisplayName("When length is null, throw an NullPointerException")
	void length_exception() {
		String str = null;
		assertThrows(NullPointerException.class, () -> {
			str.length();
		});
	}

	@Test
	@RepeatedTest(value = 5)
	void length_greater_than_zero() {
		assertTrue("ABCD".length() > 0);
		assertTrue("ABC".length() > 0);
		assertTrue("A".length() > 0);
	}

	@ParameterizedTest
	@ValueSource(strings = { "ABCD", "AB", "ABC", "123" })
	void length_greater_than_zero_using_parameterized_test(String str) {
		assertTrue(str.length() > 0);
	}

	@ParameterizedTest
	@CsvSource(value = { "abcd, ABCD", "abc, ABC", "'',''", "abcdefg, ABCDEFG" })
	void uppercase(String word, String capitalizedWord) {
		assertEquals(capitalizedWord, word.toUpperCase());
	}

	@ParameterizedTest(name = "{0} length is {1}")
	@CsvSource(value = { "abcd, 4", "abc, 3", "'',0", "abcdefg, 7" })
	void length(String word, int expectedLength) {
		assertEquals(word.length(), expectedLength);
	}

	@Test
	void performanceTest() {
		assertTimeout(Duration.ofMinutes(5), () -> {
			for (int i = 0; i < 1_000; i++) {
				int j = i;
				System.out.println(j);
			}
		});
	}

	@Test
	@Disabled
	void disabledTest() {
		System.out.println("This test will not run because its disabled.");
	}

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
	@DisplayName("For an Large strings")
	class LargeStringTests {

		@BeforeEach
		void setToEmpty() {
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
