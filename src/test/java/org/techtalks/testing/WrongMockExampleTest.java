package org.techtalks.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


class WrongMockExampleTest {

    static class WorkingHourCalculator {

        public boolean isNow() {
            var now = LocalDateTime.now();
            var today = now.toLocalDate();
            var start = today.atTime(9, 0, 0);
            var end = today.atTime(18, 0, 0);
            return now.isAfter(start) && now.isBefore(end);
        }
    }

    @Disabled("It will work eventually")
    @Test
    void isNotWorkingHour() {
        var calculator = new WorkingHourCalculator();
        Assertions.assertFalse(calculator.isNow());
    }
}
