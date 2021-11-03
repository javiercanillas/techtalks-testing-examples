package org.techtalks.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;


class MockExampleTest {

    static class TickCalculator {
        Clock clock;
        public TickCalculator() {
            this(Clock.systemDefaultZone());
        }

        public TickCalculator(final Clock clock) {
            this.clock = clock;
        }

        public long nextTick() {
            return Instant.now(clock)
                    .plus(100L, ChronoUnit.SECONDS)
                    .getLong(ChronoField.INSTANT_SECONDS);
        }
    }

    @Test
    void calculateNextTick() {
        final Clock clock = Clock.systemDefaultZone();
        var calculator = new TickCalculator(clock);
        Assertions.assertEquals(Instant.now(clock)
                        .plus(100L, ChronoUnit.SECONDS)
                        .getLong(ChronoField.INSTANT_SECONDS),
                calculator.nextTick());
    }
}
