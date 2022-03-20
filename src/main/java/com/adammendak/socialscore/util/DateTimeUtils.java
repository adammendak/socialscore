package com.adammendak.socialscore.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateTimeUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");

    public String getCreatedDateTime() {
        return FORMATTER.format(LocalDateTime.now());
    }

}
