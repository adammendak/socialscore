package com.adammendak.socialscore.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateTimeUtils {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");

    public String getCreatedDateTime() {
        return DATE_TIME_FORMATTER.format(LocalDateTime.now());
    }

    public static boolean checkIfCreatedTimeWithin5Seconds(String time) {
        return LocalTime.now().minusSeconds(5).isBefore(LocalTime.parse(time));
    }
}
