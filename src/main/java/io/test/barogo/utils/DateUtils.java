package io.test.barogo.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtils {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static LocalDateTime toStartLocalDateTime(LocalDate date) {
        return date.atTime(0, 0, 0);
    }

    public static LocalDateTime toEndLocalDateTime(LocalDate date) {
        return date.atTime(23,59,59);
    }
}
