package com.griddynamics.qa.course.calculator.reporting;

import lombok.Getter;
import com.griddynamics.qa.course.calculator.objects.Course;
import com.griddynamics.qa.course.calculator.objects.Curriculum;
import com.griddynamics.qa.course.calculator.objects.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class ReportData {
    private final List<Student> students = new ArrayList<Student>();
    private LocalDateTime reportDateTime;
    private LocalDate reportDate;
    private LocalTime reportTime;
    private boolean isShort = false;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void loadReportData(String stringDateTime, String type) {
        reportDateTime = LocalDateTime.parse(stringDateTime, formatter);

        this.reportDate = reportDateTime.toLocalDate();
        this.reportTime = reportDateTime.toLocalTime();


        if (Objects.equals(type, "0") || Objects.equals(type, "")){
            isShort = true;
        }

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

        this.students.add(student1);
        this.students.add(student2);
    }
}
