package com.griddynamics.qa.course.calculator.reporting;

import com.griddynamics.qa.course.calculator.service.WorkingHoursCalculator;
import com.griddynamics.qa.course.calculator.object.Student;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReportGenerator {

    private final WorkingHoursCalculator calculator = new WorkingHoursCalculator();
    private boolean isShort = false;

    private final LocalDateTime reportTime;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ReportGenerator(String stringDateTime, String type) {
        if (Objects.equals(type, "0") || Objects.equals(type, "")) {
            isShort = true;
        }

        reportTime = LocalDateTime.parse(stringDateTime, FORMATTER);
    }

    private String getShortReportString() {
        return "Short report as of " + reportTime;
    }

    private String getFullReportString() {
        return "Full report as of " + reportTime;
    }


    public List<String> getReport(List<Student> students) {
        List<String> result = new ArrayList<>();
        int hoursRemaining;

        if (isShort) {
            System.out.println(getShortReportString());
            for (Student student : students) {
                hoursRemaining = hoursRemaining(student);

                if (student.getStartingDate().isAfter(reportTime)) {
                    continue;
                }

                result.add(generateShortReport(student, hoursRemaining));
            }
        }
        else {
            System.out.println(getFullReportString());
            for (Student student : students) {
                hoursRemaining = hoursRemaining(student);

                if (student.getStartingDate().isAfter(reportTime)) {
                    continue;
                }

                result.add(generateFullReport(student, hoursRemaining));
            }
        }

        return result;
    }

    private String generateShortReport(Student student, int hoursRemaining) {
        StringBuilder result = new StringBuilder();
        List<Integer> timeDifference = timeDifference(student);

        result.append(student.getFirstName()).append(" ").append(student.getLastName()).append(" ").
                append("(").append(student.getCurriculum().getTitle()).append(") ");

        if (hoursRemaining <= 0) {
            result.append("Training completed. ");
            if (timeDifference.get(0) != 0) {
                result.append(timeDifference.get(0)).append(" d ").append(timeDifference.get(1)).
                        append(" hours have passed since the end.");
            }
            else {
                result.append(timeDifference.get(1)).append(" hours have passed since the end.");
            }
        }
        else {
            result.append("Training is not finished. ");
            if (timeDifference.get(0) != 0) {
                result.append(timeDifference.get(0)).append(" d ").append(timeDifference.get(1)).
                        append(" hours are left until the end.");
            }
            else {
                result.append(timeDifference.get(1)).append(" hours are left until the end.");
            }
        }

        return result.toString();
    }

    private String generateFullReport(Student student, int hoursRemaining) {
        StringBuilder result = new StringBuilder();
        List<Integer> timeDifference = timeDifference(student);

        result.append(student.getFirstName()).append(" ").append(student.getLastName()).append("\n").
                append(WorkingHoursCalculator.START.getHour()).append("-").
                append(WorkingHoursCalculator.END.getHour()).append(" working hours\n").
                append(student.getCurriculum().getTitle()).append("\n").append(student.getCurriculum().getHours()).
                append(" hours total\nStart: ").append(student.getStartingDate().format(FORMATTER)).
                append("\nFinish: ").append(getFinishDateTime(student).format(FORMATTER)).append("\n");

        if (hoursRemaining <= 0) {
            result.append("Training completed. ");
            if (timeDifference.get(0) != 0) {
                result.append(timeDifference.get(0)).append(" d ").append(timeDifference.get(1)).
                        append(" hours have passed since the end.\n");
            }
            else {
                result.append(timeDifference.get(1)).append(" hours have passed since the end.\n");
            }
        }
        else {
            result.append("Training is not finished. ");
            if (timeDifference.get(0) != 0) {
                result.append(timeDifference.get(0)).append(" d ").append(timeDifference.get(1)).
                        append(" hours are left until the end.\n");
            }
            else {
                result.append(timeDifference.get(1)).append(" hours are left until the end.\n");
            }
        }

        return result.toString();
    }

    public int hoursRemaining(Student student) {
        int required = student.getCurriculum().getHours();

        LocalDate startDate = student.getStartingDate().toLocalDate();

        int hoursDoneOnReportDay = calculator.getCurrentDayHours(reportTime.toLocalTime());
        int daysDoneToReportDay = calculator.getWorkingDaysDifference(reportTime.toLocalDate(), startDate);

        int done = hoursDoneOnReportDay + (daysDoneToReportDay * WorkingHoursCalculator.HOURS);

        return required - done;
    }

    private List<Integer> timeDifference(Student student) {
        int hoursDoneOnReportDay = calculator.getWorkingHoursDifference(reportTime.toLocalTime(),
                getFinishDateTime(student).toLocalTime());

        int daysDoneToReportDay = calculator.getWorkingDaysDifference(reportTime.toLocalDate(),
                getFinishDateTime(student).toLocalDate());

        return calculator.calculateDaysAndHours(hoursDoneOnReportDay, daysDoneToReportDay);
    }

    public LocalDateTime getFinishDateTime(Student student) {
        int hours = student.getCurriculum().getHours() % WorkingHoursCalculator.HOURS;
        int days = student.getCurriculum().getHours() / WorkingHoursCalculator.HOURS;

        if (hours == 0) {
            hours = 8;
            days = days + 1;
        }

        LocalTime finishTime = student.getStartingDate().toLocalTime().plusHours(hours);
        LocalDate finishDate = student.getStartingDate().toLocalDate().plusDays(days);

        if (finishDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            finishDate = finishDate.plusDays(2);
        }
        else if (finishDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            finishDate = finishDate.plusDays(1);
        }

        return LocalDateTime.of(finishDate, finishTime);
    }
}
