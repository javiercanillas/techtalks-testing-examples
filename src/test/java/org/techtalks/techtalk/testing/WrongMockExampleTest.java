package org.techtalks.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;


class WrongMockExampleTest {

    static class TickCalculator {

        public long nextTick() {
            return Instant.now()
                    .plus(100L, ChronoUnit.SECONDS)
                    .getLong(ChronoField.INSTANT_SECONDS);
        }
    }

    @Test
    void calculateNextTick() {
        final Clock clock = Clock.systemDefaultZone();
        var calculator = new TickCalculator();
        Assertions.assertEquals(Instant.now(clock)
                        .plus(100L,
                                ChronoUnit.SECONDS)
                        .getLong(ChronoField.INSTANT_SECONDS),
                calculator.nextTick());
    }
}
