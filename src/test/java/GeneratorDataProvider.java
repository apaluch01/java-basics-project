import com.griddynamics.qa.course.calculator.object.Course;
import com.griddynamics.qa.course.calculator.object.Curriculum;
import com.griddynamics.qa.course.calculator.object.Student;
import com.griddynamics.qa.course.calculator.reporting.ReportGenerator;
import org.testng.annotations.DataProvider;

import java.time.LocalDateTime;
import java.time.Month;

public class GeneratorDataProvider {

    @DataProvider (name = "default-data")
    public Object[][] defaultDataProvider(){
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
        Student student1 = new Student("Ivan", "Ivanov", curriculum1,
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 0));
        Student student2 = new Student("Ivan", "Sidorov", curriculum2,
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 0));

        return new Object[][] {{generator, student1, student2}};
    }

    @DataProvider (name = "student-one")
    public Object[][] studentOneProvider(){
        Course course1 = new Course("Java", 16);
        Course course2 = new Course("JBCD", 24);
        Course course3 = new Course("Spring", 16);

        Curriculum curriculum1 = new Curriculum.CurriculumBuilder().title("Java Developer").
                courses(course1).courses(course2).courses(course3).build();

        Student student1 = new Student("Ivan", "Ivanov", curriculum1,
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 0));

        return new Object[][] {{student1}};
    }

    @DataProvider (name = "student-two")
    public Object[][] studentTwoProvider(){
        Course course4 = new Course("Test Design", 10);
        Course course5 = new Course("Page Object", 16);
        Course course6 = new Course("Selenium", 16);

        Curriculum curriculum2 = new Curriculum.CurriculumBuilder().title("AQE").
                courses(course4).courses(course5).courses(course6).build();

        Student student2 = new Student("Ivan", "Sidorov", curriculum2,
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 0));

        return new Object[][] {{student2}};
    }

    @DataProvider (name = "wrong-date-format")
    public Object[][] WrongDateFormatProvider(){
        return new Object[][] {{"11-12-22 09:59"}, {"09:59 2020-06-01"}};
    }

}
