package com.griddynamics.qa.course.calculator.object;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class Student {

    @NonNull
    private String fullName;
    @NonNull
    private Curriculum curriculum;
    @NonNull
    private LocalDateTime startingDate;

}
