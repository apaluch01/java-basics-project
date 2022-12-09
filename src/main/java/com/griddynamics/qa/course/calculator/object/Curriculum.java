package com.griddynamics.qa.course.calculator.object;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class Curriculum {

    @NonNull
    private final String title;
    @NonNull
    private final List<Course> courses;
    private final int hours;

    public Curriculum(String title, List<Course> courses) {
        this.title = title;
        this.courses = courses;
        this.hours = getFullDuration(courses);
    }
    
    private int getFullDuration(List<Course> courses) {
        int sum = 0;

        for (Course course : courses) {
            sum += course.getHours();
        }

        return sum;
    }
}
