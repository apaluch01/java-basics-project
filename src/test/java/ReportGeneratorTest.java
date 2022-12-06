import com.griddynamics.qa.course.calculator.object.Course;
import com.griddynamics.qa.course.calculator.object.Curriculum;
import com.griddynamics.qa.course.calculator.object.Student;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.griddynamics.qa.course.calculator.reporting.ReportGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class ReportGeneratorTest {

    ReportGenerator generator = new ReportGenerator("2020-06-08 15:00", "o");

    Course course1 = new Course("Java", 16);
    Course course2 = new Course("JBCD", 24);
    Course course3 = new Course("Spring", 16);
    Course course4 = new Course("Test Design", 10);
    Course course5 = new Course("Page Object", 16);
    Course course6 = new Course("Selenium", 16);
    Curriculum curriculum1 = new Curriculum.CurriculumBuilder().title("Java Developer").
    courses(course1).courses(course2).courses(course3).build();
    Curriculum curriculum2 = new Curriculum.CurriculumBuilder().title("AQE").
            courses(course4).courses(course5).courses(course6).build();
    Student student1 = new Student("Ivanov Ivan", curriculum1,
                                 LocalDateTime.of(2020, Month.JUNE, 1, 10, 0));
    Student student2 = new Student("Sidorov Ivan", curriculum2,
            LocalDateTime.of(2020, Month.JUNE, 1, 10, 0));


    @Test
    public void shouldCountFinishDateCorrectlyWithSkippingWeekends() {
        Assert.assertEquals(generator.getFinishDateTime(student2),
                LocalDateTime.of(LocalDate.of(2020, 6, 8), LocalTime.of(12, 0)));
    }

    @Test
    public void hoursDividedByEightShouldStayAsEight() {
        Assert.assertEquals(generator.getFinishDateTime(student1),
                LocalDateTime.of(LocalDate.of(2020, 6, 9), LocalTime.of(18, 0)));
    }

    @Test
    public void shouldBeNegativeWhenNotCompleted() {
        generator = new ReportGenerator("2020-06-08 11:00", "o");
        Assert.assertEquals(generator.hoursRemaining(student2), 1);

        generator = new ReportGenerator("2020-06-08 11:59", "o");
        Assert.assertEquals(generator.hoursRemaining(student2), 1);

        generator = new ReportGenerator("2020-06-08 12:00", "o");
        Assert.assertEquals(generator.hoursRemaining(student2), 0);
    }
}
