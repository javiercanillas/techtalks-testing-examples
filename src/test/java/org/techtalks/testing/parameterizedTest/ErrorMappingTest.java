package org.techtalks.testing.parameterizedTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ErrorMappingTest {

    ErrorMapping errorMapping = new ErrorMapping();

    private static Stream<Arguments> mapErrorArguments() {
        return Stream.of(
                Arguments.of(0, ErrorMapping.Reason.GENERAL_FAILURE),
                Arguments.of(1, ErrorMapping.Reason.ERROR01),
                Arguments.of(10, ErrorMapping.Reason.ERROR01),
                Arguments.of(2, ErrorMapping.Reason.ERROR02),
                Arguments.of(20, ErrorMapping.Reason.ERROR02),
                Arguments.of(3, ErrorMapping.Reason.ERROR03),
                Arguments.of(999, ErrorMapping.Reason.GENERAL_FAILURE)
        );
    }

    @ParameterizedTest
    @MethodSource("mapErrorArguments")
    void mapErrorMethod(final int inputCode,
                  final ErrorMapping.Reason expectedReason) {
        var providerError = new ProviderError();
        providerError.setCode(inputCode);
        Assertions.assertEquals(expectedReason,
                errorMapping.mapError(providerError));
    }


    @ParameterizedTest
    @CsvSource({
            "0, GENERAL_FAILURE",
            "1, ERROR01",
            "2, ERROR02",
            "3, ERROR03",
            "99, GENERAL_FAILURE",
    })
    void mapErrorCsv(final int inputCode,
                  final ErrorMapping.Reason expectedReason) {
        var providerError = new ProviderError();
        providerError.setCode(inputCode);
        Assertions.assertEquals(expectedReason,
                errorMapping.mapError(providerError));
    }
}

