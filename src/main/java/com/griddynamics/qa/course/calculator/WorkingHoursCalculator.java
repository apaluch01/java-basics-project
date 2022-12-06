package com.griddynamics.qa.course.calculator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.time.temporal.ChronoUnit.DAYS;

public class WorkingHoursCalculator {

    public static final int HOURS = 8;
    public static final LocalTime START = LocalTime.of(10, 0);
    public static final LocalTime END = LocalTime.of(18, 0);

    public int getCurrentDayHours(LocalTime currentTime) {
        int difference = 0;

        if (currentTime.isAfter(START) && currentTime.isBefore(END)) {
            difference = currentTime.minusHours(START.getHour()).getHour();
        }
        else if (currentTime.isAfter(END) || currentTime.equals(END)) {
            difference = HOURS;
        }

        return difference;
    }

    public int getWorkingHoursDifference(LocalTime reportTime, LocalTime courseTime) {
        long difference = abs(reportTime.getHour() - courseTime.getHour());

        return (int) difference;
    }

    public int getWorkingDaysDifference(LocalDate reportDate, LocalDate startDate) {
        long difference = DAYS.between(startDate, reportDate);
        LocalDate courseDays = startDate;

        while (courseDays.isBefore(reportDate)){
            if (courseDays.getDayOfWeek() == DayOfWeek.SATURDAY || courseDays.getDayOfWeek() == DayOfWeek.SUNDAY) {
                difference = difference - 1;
            }
            courseDays = courseDays.plusDays(1);
        }



        return (int) abs(difference);
    }

    public List<Integer> calculateDaysAndHours(int hours, int days) {
        List<Integer> result = new ArrayList<>();

        if (hours == 8) {
            hours = 0;
            days = days + 1;
        }

        result.add(0, days);
        result.add(1, hours);

        return result;
    }
}
