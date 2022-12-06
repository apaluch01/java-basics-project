package com.griddynamics.qa.course.calculator.object;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class Student {

    @NonNull
    private final String firstName;
    @NonNull
    private final String lastName;
    @NonNull
    private final Curriculum curriculum;
    @NonNull
    private final LocalDateTime startingDate;

}
