package org.techtalks.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
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

    @Disabled
    @Test
    void calculateNextTick() throws InterruptedException {
        var calculator = new TickCalculator();
        final var expectedNextTick = Instant.now()
                .plus(100L,
                        ChronoUnit.SECONDS)
                .getLong(ChronoField.INSTANT_SECONDS);

        Thread.sleep(500);
        
        Assertions.assertEquals(expectedNextTick,
                calculator.nextTick());
    }
}
