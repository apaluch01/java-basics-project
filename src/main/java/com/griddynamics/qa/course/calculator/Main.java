package com.griddynamics.qa.course.calculator;
import com.griddynamics.qa.course.calculator.reporting.ReportGenerator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ReportGenerator generator;

        Scanner input = new Scanner(System.in);

        System.out.println("Enter date for report generation in YYYY-MM-DD HH:MM format");
        String reportDate = input.nextLine();

        System.out.println("Enter 0 or press enter for a short report version");
        String reportType = input.nextLine();

        generator = new ReportGenerator(reportDate, reportType);
        generator.getReport().stream().forEach(r -> System.out.println(r));
    }
}
