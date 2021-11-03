package org.techtalks.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


class MockExampleTest {

    static class WorkingHourCalculator {
        Clock clock;

        public WorkingHourCalculator() {
            this(Clock.systemDefaultZone());
        }

        public WorkingHourCalculator(final Clock clock) {
            this.clock = clock;
        }

        public boolean isNow() {
            var now = LocalDateTime.now(clock);
            var today = now.toLocalDate();
            var start = today.atTime(9, 0, 0);
            var end = today.atTime(18, 0, 0);
            return now.isAfter(start) && now.isBefore(end);
        }
    }

    @Test
    void isNotWorkingHour() {
        final Clock clock = Clock.fixed(Instant.parse("2021-11-03T10:15:30.00Z"),
                ZoneId.of("America/Sao_Paulo"));
        var calculator = new WorkingHourCalculator(clock);
        Assertions.assertFalse(calculator.isNow());
    }
}
