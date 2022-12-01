import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.DAYS;

public class WorkingHoursCalculator {
    static final int HOURS = 8;
    static final LocalTime START = LocalTime.of(10, 0);
    static final LocalTime END = LocalTime.of(18, 0);

    public int getWorkingHoursDifference(LocalTime currentTime){
        int difference = 0;

        if (currentTime.isAfter(START) && currentTime.isBefore(END)){
            difference = currentTime.minusHours(START.getHour()).getHour();
        } else if (currentTime.isAfter(END) || currentTime.equals(END)) {
            difference = HOURS;
        }

        return difference;
    }

    public int getWorkingDaysDifference(LocalDate currentDate, LocalDate startDate){
        long difference = DAYS.between(startDate, currentDate);
        LocalDate courseDays = startDate;

        while (courseDays.isBefore(currentDate)){
            if (courseDays.getDayOfWeek() == DayOfWeek.SATURDAY || courseDays.getDayOfWeek() == DayOfWeek.SUNDAY){
                difference = difference - 1;
            }
            courseDays = courseDays.plusDays(1);
        }

        return (int) difference;
    }

}
