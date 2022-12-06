package com.griddynamics.qa.course.calculator.object;

import lombok.Data;
import lombok.NonNull;

@Data
public class Course {

    @NonNull
    private final String title;
    @NonNull
    private final int hours;

}
