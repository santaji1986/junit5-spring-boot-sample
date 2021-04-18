
# Junit 5 Jupiter Framework

## Examples for Junit5 unit testing


1.  Examples of @ParameterizedTest

a.  @ParameterizedTest using @ValueSource

```java
    @ParameterizedTest
    @ValueSource(strings = { "ABCD", "AB", "ABC", "123" })
    void length_greater_than_zero_using_parameterized_test(String str) {
        assertTrue(str.length() > 0);
    }
```
b. @ParameterizedTest using @EnumSource  

```java
    @ParameterizedTest(name = "Role {0} is allowed.")
    @EnumSource(value = Roles.class, mode = Mode.EXCLUDE, names = "READER")
    void accessToRoles(Roles role) throws Exception {
        assertThat(role).isNotEqualByComparingTo(Roles.READER);
    }
```
c. @ParameterizedTest using @CsvSource  

```java
	 @ParameterizedTest(name = "{0} length is {1}")
    @CsvSource(value = { "abcd, 4", "abc, 3" })
    void length(String word, int expectedLength) {
        assertEquals(word.length(), expectedLength);
    }
```
d. @ParameterizedTest using @ArgumentsSource  

```java
    @ParameterizedTest(name = "Collection {0} contains {1} elements.")
    @ArgumentsSource(MyArgumentsProvider.class)
    void testCollectionSize(Collection<Object> list, int expectedSize) {
        assertThat(list).hasSize(expectedSize);
    }

    private static class MyArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(Collections.emptyList(), 0),
                    Arguments.of(Collections.singleton(true), 1),
                    Arguments.of(List.of(99, 100), 2),
                    Arguments.of(Set.of("foo", "bar", "baz"), 3));
        }
    }
```

2.  @RepeatedTest  

```java
@DisplayName("Add operation test")
    @RepeatedTest(value = 5, name = "{displayName} - repetition {currentRepetition} of {totalRepetitions}")
    void addNumber(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println("Running addNumber test -> " + repetitionInfo.getCurrentRepetition());
        assertEquals(2, Math.addExact(1, 1), "1 + 1 should equal 2");
    }
```


3. 	@Nested  

```java

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
```
Example of performanceTest  

```java
@Test
    void performanceTest() {
        assertTimeout(Duration.ofMinutes(5), () -> {
            for (int i = 0; i < 1_0; i++) {
                final int j = i;
                System.out.println(j);
            }
        });
    }
```



