import com.griddynamics.qa.course.calculator.WorkingHoursCalculator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class CalculatorDaysTest {

    WorkingHoursCalculator calculator = new WorkingHoursCalculator();

    @Test
    public void shouldCountWeekdaysBetweenMonths() {
        Assert.assertEquals(calculator.getWorkingDaysDifference(LocalDate.of(2022, Month.NOVEMBER, 4),
                LocalDate.of(2022, Month.OCTOBER, 31)), 4);
    }

    @Test
    public void shouldCountOnlyWeekdays() {
        Assert.assertEquals(calculator.getWorkingDaysDifference(LocalDate.of(2022, Month.DECEMBER, 31),
                LocalDate.of(2022, Month.DECEMBER, 1)), 22);
    }

    @Test
    public void shouldConsiderLeapYear() {
        Assert.assertEquals(calculator.getWorkingDaysDifference(LocalDate.of(2023, Month.MARCH, 4),
                LocalDate.of(2023, Month.FEBRUARY, 27)), 5);
        Assert.assertEquals(calculator.getWorkingDaysDifference(LocalDate.of(2024, Month.MARCH, 2),
                LocalDate.of(2024, Month.FEBRUARY, 26)), 5);
    }

    @Test
    public void shouldCountWeekdaysBetweenYears() {
        Assert.assertEquals(calculator.getWorkingDaysDifference(LocalDate.of(2025, Month.JANUARY, 4),
                LocalDate.of(2024, Month.DECEMBER, 30)), 5);
    }

    @Test
    public void shouldReturnZeroForSameDate() {
        Assert.assertEquals(calculator.getWorkingDaysDifference(LocalDate.of(2022, Month.DECEMBER, 2),
                LocalDate.of(2022, Month.DECEMBER, 2)), 0);
    }

    @Test
    public void daysShouldRemainAsZero() {
        List<Integer> checker = new ArrayList<>();
        checker.add(0, 0);
        checker.add(1, 7);
        Assert.assertEquals(calculator.calculateDaysAndHours(7, 0), checker);
    }

    @Test
    public void dayDifferenceShouldBeAbsolute() {
        Assert.assertEquals(calculator.getWorkingDaysDifference(LocalDate.of(2022, 12, 1),
                LocalDate.of(2022, 12, 2)), 1);
        Assert.assertEquals(calculator.getWorkingDaysDifference(LocalDate.of(2022, 12, 2),
                LocalDate.of(2022, 12, 1)), 1);
    }
}
