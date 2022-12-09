package com.griddynamics.qa.course.calculator;
import com.griddynamics.qa.course.calculator.object.Course;
import com.griddynamics.qa.course.calculator.object.Curriculum;
import com.griddynamics.qa.course.calculator.object.Student;
import com.griddynamics.qa.course.calculator.reporting.ReportGenerator;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student("Ivan", "Ivanov",
                new Curriculum("Java Developer", Arrays.asList(new Course("Java", 16),
                    new Course("JBCD", 24), new Course("Spring", 16))),
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 0)),
            new Student("Ivan", "Sidorov",
                    new Curriculum("AQE",
                    Arrays.asList(new Course("Test Design", 10),
                            new Course("Page Object", 16), new Course("Selenium", 16))),
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 0)));

    public static void main(String[] args) {
        ReportGenerator generator;

        Scanner input = new Scanner(System.in);

        System.out.println("Enter date for report generation in YYYY-MM-DD HH:MM format");
        String reportDate = input.nextLine();

        System.out.println("Enter 0 or press enter for a short report version");
        String reportType = input.nextLine();

        generator = new ReportGenerator(reportDate, reportType);
        generator.getReport(STUDENTS).forEach(System.out::println);
    }
}
