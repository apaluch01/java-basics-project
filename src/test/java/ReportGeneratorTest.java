import com.griddynamics.qa.course.calculator.object.Student;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.griddynamics.qa.course.calculator.reporting.ReportGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class ReportGeneratorTest {

    ReportGenerator generator;

    @Test (dataProvider = "default-data", dataProviderClass = GeneratorDataProvider.class)
    public void shouldCountFinishDateCorrectlyWithSkippingWeekends(ReportGenerator defaultGenerator,
                                                                   Student student1, Student student2) {
        Assert.assertEquals(defaultGenerator.getFinishDateTime(student2),
                LocalDateTime.of(LocalDate.of(2020, 6, 8), LocalTime.of(12, 0)));
    }

    @Test (dataProvider = "default-data", dataProviderClass = GeneratorDataProvider.class)
    public void hoursDividedByEightShouldStayAsEight(ReportGenerator defaultGenerator,
                                                     Student student1, Student student2) {
        Assert.assertEquals(defaultGenerator.getFinishDateTime(student1),
                LocalDateTime.of(LocalDate.of(2020, 6, 9), LocalTime.of(18, 0)));
    }

    @Test (dataProvider = "student-two", dataProviderClass = GeneratorDataProvider.class)
    public void shouldBeNegativeWhenNotCompleted(Student student2) {
        generator = new ReportGenerator("2020-06-08 11:00", "o");
        Assert.assertEquals(generator.hoursRemaining(student2), 1);

        generator = new ReportGenerator("2020-06-08 11:59", "o");
        Assert.assertEquals(generator.hoursRemaining(student2), 1);

        generator = new ReportGenerator("2020-06-08 12:00", "o");
        Assert.assertEquals(generator.hoursRemaining(student2), 0);
    }

    @Test
    public void studentsNotYetLearningShouldntBeReported() {
        generator = new ReportGenerator("2020-06-01 09:59", "");

        Assert.assertTrue(generator.getReport().isEmpty());
    }

    @Test (expectedExceptions = {DateTimeParseException.class}, dataProvider = "wrong-date-format",
            dataProviderClass = GeneratorDataProvider.class)
    public void wrongDateFormatShouldThrowException(String date) {
        generator = new ReportGenerator(date, "");
    }
}
