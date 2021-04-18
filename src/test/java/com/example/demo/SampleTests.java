package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;
import org.junit.jupiter.params.provider.ValueSource;

public class SampleTests {
    @ParameterizedTest
    @ValueSource(ints = { 1, 10, 38 })
    void testPositiveNumber(int input) throws Exception {
        assertThat(input).isPositive();
    }

    @ParameterizedTest(name = "Role {0} is allowed.")
    @EnumSource(value = Roles.class, mode = Mode.EXCLUDE, names = "READER")
    void accessToRoles(Roles role) throws Exception {
        assertThat(role).isNotEqualByComparingTo(Roles.READER);
    }

    @ParameterizedTest(name = "Date {0} add months {1} equals {2}")
    @CsvSource({ "2021-04-15, P4M, AUGUST", "2021-05-15, P3M, AUGUST", })
    void testDatePeriodMonth(LocalDate date, Period period, Month month) throws Exception {
        assertThat(date.getMonth().plus(period.getMonths())).isEqualTo(month);
    }

    @ParameterizedTest(name = "Collection {0} contains {1} elements.")
    @ArgumentsSource(MyArgumentsProvider.class)
    void testCollectionSize(Collection<Object> list, int expectedSize) {
        assertThat(list).hasSize(expectedSize);
    }

    private static class MyArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            // @formatter:off
            return Stream.of(
                    Arguments.of(Collections.emptyList(), 0),
                    Arguments.of(Collections.singleton(true), 1),
                    Arguments.of(List.of(99, 100), 2),
                    Arguments.of(Set.of("foo", "bar", "baz"), 3));
            // @formatter:on

        }
    }

}
