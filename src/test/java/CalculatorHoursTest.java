import com.griddynamics.qa.course.calculator.service.WorkingHoursCalculator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalTime;

public class CalculatorHoursTest {

    WorkingHoursCalculator calculator = new WorkingHoursCalculator();

    @Test
    public void returnZeroBeforeStartingHour() {
        Assert.assertEquals(calculator.getCurrentDayHours(LocalTime.of(0, 0)), 0);
        Assert.assertEquals(calculator.getCurrentDayHours(LocalTime.of(9, 59, 59)), 0);
        Assert.assertEquals(calculator.getCurrentDayHours(LocalTime.of(10, 0)), 0);
    }

    @Test
    public void returnCorrectFullHours() {
        Assert.assertEquals(calculator.getCurrentDayHours(LocalTime.of(10, 59, 59)), 0);
        Assert.assertEquals(calculator.getCurrentDayHours(LocalTime.of(11, 0)), 1);
    }

    @Test
    public void returnCorrectBeforeEndingHour() {
        Assert.assertEquals(calculator.getCurrentDayHours(LocalTime.of(17, 59, 59)), 7);
        Assert.assertEquals(calculator.getCurrentDayHours(LocalTime.of(18, 0)), 8);
    }

    @Test
    public void returnEightAfterEndingHour() {
        Assert.assertEquals(calculator.getCurrentDayHours(LocalTime.of(18, 0)), 8);
        Assert.assertEquals(calculator.getCurrentDayHours(LocalTime.of(23, 59, 59)), 8);
    }

    @Test
    public void hourDifferenceShouldBeAbsolute() {
        LocalTime time1 = LocalTime.of(15, 0);
        LocalTime time2 = LocalTime.of(18, 0);
        Assert.assertEquals(calculator.getWorkingHoursDifference(time1, time2), 3);
        Assert.assertEquals(calculator.getWorkingHoursDifference(time2, time1), 3);
    }
}
