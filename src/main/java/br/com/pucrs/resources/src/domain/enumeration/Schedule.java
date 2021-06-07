package br.com.pucrs.resources.src.domain.enumeration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Schedule {

    AB("08:00 AM - 09:30 AM "),
    CD("09:45 AM - 11:15 AM "),
    EE1("11:30 AM - 13:00 PM "),
    FG("14:00 PM - 15:30 PM "),
    HI("15:45 PM - 17:15 PM "),
    JK("17:30 PM - 19:00 PM "),
    LM("19:15 PM - 20:45 PM "),
    NP("21:00 PM - 22:30 PM ");

    private final String description;

    public static Schedule get(final String value) {
        return Arrays.stream(Schedule.values())
                .filter(schedule -> schedule.name().equals(value))
                .findFirst()
                .orElse(null);
    }
}
