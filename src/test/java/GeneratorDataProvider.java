import com.griddynamics.qa.course.calculator.object.Course;
import com.griddynamics.qa.course.calculator.object.Curriculum;
import com.griddynamics.qa.course.calculator.object.Student;
import com.griddynamics.qa.course.calculator.reporting.ReportGenerator;
import org.testng.annotations.DataProvider;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class GeneratorDataProvider {

    @DataProvider (name = "default-data")
    public Object[][] defaultDataProvider(){
        ReportGenerator generator = new ReportGenerator("2020-06-08 15:00", "o");

        Student student1 = new Student("Ivan", "Ivanov",
                new Curriculum("Java Developer", Arrays.asList(new Course("Java", 16),
                        new Course("JBCD", 24), new Course("Spring", 16))),
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 0));
        Student student2 = new Student("Ivan", "Sidorov",
                new Curriculum("AQE",
                        Arrays.asList(new Course("Test Design", 10),
                                new Course("Page Object", 16), new Course("Selenium", 16))),
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 0));

        return new Object[][] {{generator, student1, student2}};
    }

    @DataProvider (name = "student-one")
    public Object[][] studentOneProvider(){
        return new Object[][] {{new Student("Ivan", "Ivanov",
                new Curriculum("Java Developer", Arrays.asList(new Course("Java", 16),
                        new Course("JBCD", 24), new Course("Spring", 16))),
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 0))}};
    }

    @DataProvider (name = "student-two")
    public Object[][] studentTwoProvider(){
        return new Object[][] {{new Student("Ivan", "Sidorov",
                new Curriculum("AQE",
                        Arrays.asList(new Course("Test Design", 10),
                                new Course("Page Object", 16), new Course("Selenium", 16))),
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 0))}};
    }

    @DataProvider (name = "wrong-date-format")
    public Object[][] WrongDateFormatProvider(){
        return new Object[][] {{"11-12-22 09:59"}, {"09:59 2020-06-01"}};
    }

}
