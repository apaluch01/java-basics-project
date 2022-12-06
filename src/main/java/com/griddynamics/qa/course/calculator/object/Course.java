package com.griddynamics.qa.course.calculator.object;

import lombok.Data;
import lombok.NonNull;

@Data
public class Course {

    @NonNull
    private String title;
    @NonNull
    private int hours;

}
